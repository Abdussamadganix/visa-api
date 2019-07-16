package com.visa.service.exception;

/**
 * @author Abdussamad
 */
public abstract class VisaApiException extends RuntimeException {

  public VisaApiException(String message) {
    super(message);
  }

  public VisaApiException(Throwable cause) {
    super(cause);
  }
}
