package com.visa.service.visa.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.threetenbp.ThreeTenModule;
import com.sun.istack.Nullable;
import com.visa.service.exception.BadRequestException;
import com.visa.service.exception.ProcessingException;
import com.visa.service.model.entity.Log;
import com.visa.service.repository.LogRepository;
import com.visa.service.util.RestTemplateErrorHandler;
import com.visa.service.util.RestUtil;
import com.visa.service.util.SecurityUtil;
import com.visa.service.visa.configuration.VisaConfiguration;
import com.visa.service.visa.util.CustomInstantDeserializer;
import java.io.File;
import java.io.FileInputStream;
import java.net.URI;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import org.apache.http.HttpHost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.threeten.bp.Instant;
import org.threeten.bp.OffsetDateTime;
import org.threeten.bp.ZonedDateTime;

/**
 * @author Abdussamad
 */
@Component
public class VisaHttpClient {


  private RestTemplate restTemplate = new RestTemplate();
  private final Logger LOGGER = LoggerFactory.getLogger(VisaHttpClient.class);
  private static final String SYSTEM_NAME = "VISA";
  private static ObjectMapper objectMapper = new ObjectMapper();
  private final LogRepository logRepository;
  private final ObjectMapper mapper;
  private final VisaConfiguration configuration;

  public VisaHttpClient(LogRepository logRepository, VisaConfiguration configuration,
      RestTemplateBuilder restTemplateBuilder,
      RestTemplateErrorHandler restTemplateErrorHandler) {
    Assert.notNull(logRepository);
    Assert.notNull(configuration);
    this.logRepository = logRepository;
    this.mapper = objectMapper;
    this.configuration = configuration;
    this.restTemplate = getRestTemplateForMutualAuth();
    restTemplate.setErrorHandler(restTemplateErrorHandler);

//    this.restTemplate = restTemplateBuilder
//        .errorHandler(restTemplateErrorHandler)
//        .build();
  }

  public <T> T get(String requestPath, Class<T> responseClass, Map<String, String> headers) {
    HttpHeaders requestHeaders = new HttpHeaders();
    requestHeaders.setContentType(MediaType.APPLICATION_JSON);
    headers.forEach(requestHeaders::set);
    LOGGER.info("Headers " + headers);
    try {
//      URI uri = new URI(requestPath);
//      return restTemplate.getForObject(uri, responseClass);
      HttpEntity<?> requestEntity = new HttpEntity<>("", requestHeaders);
      ResponseEntity<String> responseEntity =
          restTemplate.exchange(requestPath, HttpMethod.GET, requestEntity, String.class);
      return objectMapper
          .readValue(responseEntity.getBody(), responseClass);
    } catch (Exception e) {
      LOGGER.error("Request failed", e);
      throw new ProcessingException(e.getMessage());
    }
  }

  public <T> T post(
      String url, Object requestObject, Class<T> responseClass,
      @Nullable Map<String, String> headers) {
    Log log = new Log();
    log.setSystem(SYSTEM_NAME);
    log.setRequestBody(requestObject.toString());
    Log firstSave = logRepository.save(log);
    HttpServerErrorException httpServerErrorException;
    try {
      LOGGER.info("Headers " + headers);
      firstSave.setRequestBody(objectMapper.writeValueAsString(requestObject));
      Log savedLog = logRepository.save(log);
      LOGGER.info(
          "request payload to Visa : " + objectMapper.writeValueAsString(requestObject));
      HttpHeaders requestHeaders = new HttpHeaders();
      requestHeaders.setContentType(MediaType.APPLICATION_JSON);
      List<MediaType> acceptableMediaTypes = new ArrayList<>();
      acceptableMediaTypes.add(MediaType.APPLICATION_JSON);
      acceptableMediaTypes.add(MediaType.APPLICATION_OCTET_STREAM);
      requestHeaders.setAccept(acceptableMediaTypes);
      if (headers != null) {
        headers.forEach(requestHeaders::set);
      }
      HttpEntity<?> requestEntity = new HttpEntity<>(requestObject, requestHeaders);
      ResponseEntity<Object> responseEntity =
          restTemplate.exchange(url, HttpMethod.POST, requestEntity, Object.class, requestObject);
      return checkResponseEntityAndBuildResponse(responseEntity, savedLog, responseClass);
    } catch (HttpServerErrorException ex) {
      LOGGER.error("Status Code from Visa (Error): " + ex.getStatusCode());
      LOGGER.error("response from Visa (Error): " + ex.getResponseBodyAsString());
      httpServerErrorException = ex;
    } catch (Exception e) {
      LOGGER.error("Request failed", e);
      LOGGER.error("response from Visa (Error): " + e.getMessage());
      throw new ProcessingException("response from Visa (Error): " + e.getMessage());
    }
    throw new ProcessingException(httpServerErrorException.getResponseBodyAsString());
  }

  public <T> T post(String url, Object requestObject, Class<T> responseClass) {
    return post(url, requestObject, responseClass, null);
  }

  private <T> T checkResponseEntityAndBuildResponse(ResponseEntity responseEntity, Log savedLog,
      Class<T> responseClass) throws Exception {
    LOGGER.info("response payload from Visa : " + objectMapper
        .writeValueAsString(responseEntity.getBody()));
    LOGGER.info(
        "response HTTP status code from Visa : " + responseEntity.getStatusCode()
            .toString());
    savedLog.setResponseBody(objectMapper.writeValueAsString(responseEntity.getBody()));
    savedLog.setResponseCode(responseEntity.getStatusCode().toString());
    logRepository.save(savedLog);
    if (responseEntity.getStatusCode().is2xxSuccessful()) {
      return objectMapper
          .readValue(objectMapper.writeValueAsString(responseEntity.getBody()), responseClass);
    } else if (RestUtil.isError(responseEntity.getStatusCode())) {
      return objectMapper
          .readValue(objectMapper.writeValueAsString(responseEntity.getBody()),
              responseClass);
    } else {
      throw new ProcessingException("Request Failed");
    }
  }


  protected RestTemplate getRestTemplateForMutualAuth() {
    try {
      KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
      FileInputStream keystoreInputStream = new FileInputStream(configuration.getKeyStorePath());
      keystore.load(keystoreInputStream, configuration.getKeyStorePassword().toCharArray());
      keystoreInputStream.close();

      SSLContext sslcontext = SSLContexts.custom().useProtocol("TLS")
          .loadKeyMaterial(keystore, configuration.getKeyStorePassword().toCharArray())
          .loadTrustMaterial(new File(configuration.getKeyStorePath()),
              configuration.getKeyStorePassword().toCharArray())
          .build();

      HostnameVerifier hostnameverifier = null;
      SSLConnectionSocketFactory sslSocketFactory = new SSLConnectionSocketFactory(sslcontext, null,
          null,
          hostnameverifier);
      CloseableHttpClient httpClient =
//          proxyHostName != null && proxyHostName != "" && proxyPortNumber != 0
//              ? HttpClients.custom().setSSLSocketFactory(sslSocketFactory)
//              .setProxy(new HttpHost(proxyHostName, proxyPortNumber)).build()
//              :
          HttpClients.custom().setSSLSocketFactory(sslSocketFactory).build();

      HttpsURLConnection.setDefaultSSLSocketFactory(sslcontext.getSocketFactory());
      HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();

      requestFactory.setHttpClient(httpClient);
      RestTemplate restTemplate = new RestTemplate(requestFactory);

      for (HttpMessageConverter converter : restTemplate.getMessageConverters()) {
        if (converter instanceof AbstractJackson2HttpMessageConverter) {
          ObjectMapper mapper = ((AbstractJackson2HttpMessageConverter) converter)
              .getObjectMapper();
          ThreeTenModule module = new ThreeTenModule();
          module.addDeserializer(Instant.class, CustomInstantDeserializer.INSTANT);
          module.addDeserializer(OffsetDateTime.class, CustomInstantDeserializer.OFFSET_DATE_TIME);
          module.addDeserializer(ZonedDateTime.class, CustomInstantDeserializer.ZONED_DATE_TIME);
          mapper.registerModule(module);
        }
      }
      restTemplate.setRequestFactory(
          new BufferingClientHttpRequestFactory(restTemplate.getRequestFactory()));
      return restTemplate;

    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
}
