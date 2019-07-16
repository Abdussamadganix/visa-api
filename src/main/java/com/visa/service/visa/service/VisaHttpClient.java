package com.visa.service.visa.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.istack.Nullable;
import com.visa.service.exception.BadRequestException;
import com.visa.service.exception.ProcessingException;
import com.visa.service.model.entity.Log;
import com.visa.service.repository.LogRepository;
import com.visa.service.util.RestTemplateErrorHandler;
import com.visa.service.util.RestUtil;
import com.visa.service.util.SecurityUtil;
import com.visa.service.visa.configuration.VisaConfiguration;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

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
    this.restTemplate = restTemplateBuilder
        .errorHandler(restTemplateErrorHandler)
        .build();
  }

  public <T> T get(String requestPath, Class<T> responseClass, Map<String, String> headers) {
    HttpHeaders requestHeaders = new HttpHeaders();
    requestHeaders.setContentType(MediaType.APPLICATION_JSON);
    headers.forEach(requestHeaders::set);
    LOGGER.info("Headers " + headers);
    try {
      URI uri = new URI(requestPath);
      return restTemplate.getForObject(uri, responseClass);
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
//      HttpHeaders headersChecl = new HttpHeaders();
//      headers.add("Authorization", SecurityUtil.base64Converter(configuration.getUsername() + ":" + configuration.getPassword()));
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

}
