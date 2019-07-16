package com.visa.service.visa.model.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Abdussamad
 */
public class AliasType {

  private static Map<String, String> aliasType = new HashMap<>();

  private static String DEFAULT_TYPE = "01";

  static {
    aliasType.put("PHONE_NUMBER", "01");
    aliasType.put("EMAIL", "02");
    aliasType.put("NATIONAL_ID", "03");
    aliasType.put("IBAN", "04");

  }

  public static Map<String, String> getAliasType() {
    return aliasType;
  }

  public static String getAliasType(String code) {
    String errorCode = aliasType.get(code);
    return (errorCode != null) ? errorCode : DEFAULT_TYPE;
  }

}
