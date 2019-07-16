package com.visa.service.exception;

/**
 * @author Abdussamad
 */
public class ProcessingException extends VisaApiException {

  public ProcessingException(String message) {
    super(message);
  }

  public ProcessingException(Throwable cause) {
    super(cause);
  }
}
