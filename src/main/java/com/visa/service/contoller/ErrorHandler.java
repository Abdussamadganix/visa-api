package com.visa.service.contoller;

import com.visa.service.exception.AuthenticationException;
import com.visa.service.exception.BadRequestException;
import com.visa.service.exception.ConflictException;
import com.visa.service.exception.NotFoundException;
import com.visa.service.exception.ProcessingException;
import com.visa.service.model.constant.ErrorCode;
import com.visa.service.model.response.ErrorResponse;
import com.visa.service.util.LoggingUtil;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author Abdussamad
 */
@ControllerAdvice
public class ErrorHandler {

  private final LoggingUtil loggingUtil;

  public ErrorHandler(LoggingUtil loggingUtil) {
    Assert.notNull(loggingUtil);
    this.loggingUtil = loggingUtil;
  }

  @ExceptionHandler(BadRequestException.class)
  public ResponseEntity<ErrorResponse> handleBadRequest(
      HttpServletRequest request, Exception e) {
    ErrorResponse response = ErrorResponse.builder()
        .error(ErrorCode.INPUT)
        .message(e.getMessage())
        .build();
    logError(request, e);
    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(AuthenticationException.class)
  public ResponseEntity<ErrorResponse> handleUnauthorized(
      HttpServletRequest request, AuthenticationException e) {
    ErrorResponse response = ErrorResponse.builder()
        .error(ErrorCode.PERMISSION)
        .message(e.getMessage())
        .build();
    logError(request, e);
    return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
  }

  private void logError(HttpServletRequest request, Exception e) {
    StringBuilder requestDescription = new StringBuilder("Error sending ")
        .append(request.getMethod())
        .append(" request to ")
        .append(request.getRequestURI());
    if (!StringUtils.isEmpty(request.getQueryString())) {
      requestDescription.append(" with query: ").append(request.getQueryString());
    }
    requestDescription.append(". Error: ");
    loggingUtil.error(requestDescription.toString() + e.getMessage(), e);
  }

  @ExceptionHandler(ConflictException.class)
  public ResponseEntity<ErrorResponse> handleConflict
      (HttpServletRequest request, ConflictException e) {
    ErrorResponse response = ErrorResponse.builder()
        .error(ErrorCode.INPUT)
        .message(e.getMessage())
        .build();
    logError(request, e);
    return new ResponseEntity<>(response, HttpStatus.CONFLICT);
  }

  @ExceptionHandler(ProcessingException.class)
  public ResponseEntity<ErrorResponse> handleProcessing
      (HttpServletRequest request, ProcessingException e) {
    ErrorResponse response = ErrorResponse.builder()
        .error(ErrorCode.PROCESSING)
        .message(e.getMessage())
        .build();
    logError(request, e);
    return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<ErrorResponse> handleNotFound
      (HttpServletRequest request, NotFoundException e) {
    ErrorResponse response = ErrorResponse.builder()
        .error(ErrorCode.NOT_FOUND)
        .message(e.getMessage())
        .build();
    logError(request, e);
    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleInternalError
      (HttpServletRequest request, Exception e) {
    ErrorResponse response = ErrorResponse.builder()
        .error(ErrorCode.PROCESSING)
        .message(e.getMessage())
        .build();
    logError(request, e);
    return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
