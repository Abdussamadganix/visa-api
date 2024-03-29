package com.visa.service.util;

import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

/**
 * @author Abdussamad
 */
@Configuration
public class RestTemplateErrorHandler implements ResponseErrorHandler {

  private static final Logger log = LoggerFactory
      .getLogger(RestTemplateErrorHandler.class);

  @Override
  public void handleError(ClientHttpResponse response) throws IOException {
    log.error("Response error: {} {}", response.getStatusCode(), response.getStatusText());
  }

  @Override
  public boolean hasError(ClientHttpResponse response) throws IOException {
    return RestUtil.isError(response.getStatusCode());
  }
}
