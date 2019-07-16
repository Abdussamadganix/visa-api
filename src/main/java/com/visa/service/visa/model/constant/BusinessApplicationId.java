package com.visa.service.visa.model.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by abdussamad.olaiya on 24/06/2019.
 */
public class BusinessApplicationId {

  private static Map<String, String> aliasType = new HashMap<>();

  private static String DEFAULT_TYPE = "01";

  static {
    aliasType.put("PERSONAL_PAYMENT", "PP");
    aliasType.put("MERCHANT_PAYMENT", "MP");
    aliasType.put("CASH_OUT", "CO");
    aliasType.put("CASH_IN", "CI");

  }

  public static Map<String, String> geBusinessApplicationType() {
    return aliasType;
  }

  public static String geBusinessApplicationType(String code) {
    String errorCode = aliasType.get(code);
    return (errorCode != null) ? errorCode : DEFAULT_TYPE;
  }

}
