package com.visa.service.util;

import org.springframework.http.HttpStatus;

/**
 * @author Abdussamad
 */
public class RestUtil {

  public static boolean isError(HttpStatus status) {
    HttpStatus.Series series = status.series();
    return (HttpStatus.Series.CLIENT_ERROR.equals(series)
        || HttpStatus.Series.SERVER_ERROR.equals(series));
  }

}
