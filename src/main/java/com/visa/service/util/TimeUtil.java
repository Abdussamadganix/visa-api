package com.visa.service.util;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author Abdussamad
 */
public class TimeUtil {

  public static String getIsoTime(Timestamp timestamp) {
    if (timestamp == null) {
      return null;
    }
    DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
    return timestamp.toLocalDateTime().format(formatter);
  }

  public static String getIsoTime(Date date) {
    if (date == null) {
      return null;
    }
    Timestamp timestamp = new Timestamp(date.getTime());
    return getIsoTime(timestamp);
  }

  public static Timestamp now() {
    return new Timestamp(LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli());
  }

  public static Timestamp futureTime(int seconds) {
    LocalDateTime futureTime = LocalDateTime.now().plusSeconds(seconds);
    return new Timestamp(futureTime.toInstant(ZoneOffset.UTC).toEpochMilli());
  }

  @SuppressWarnings("WeakerAccess")
  public static Timestamp futureMonths(int months) {
    LocalDateTime futureTime = LocalDateTime.now().plusMonths(months);
    return new Timestamp(futureTime.toInstant(ZoneOffset.UTC).toEpochMilli());
  }

  public static Timestamp pastTime(int seconds) {
    return futureTime(-1 * seconds);
  }

  public static Timestamp pastMonths(int months) {
    return futureMonths(-1 * months);
  }

  public static LocalDateTime localDateTimefromEpochMilli(Long timestamp) {
    return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneOffset.UTC);
  }
}
