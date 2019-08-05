package com.visa.service.model.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Abdussamad
 */
public class PointOfInitiationMethod {

  private static Map<InitiationMethod, String> statusCodes = new HashMap<>();

  private static String QR_DYNAMIC_CODE = "12";

  static {
    statusCodes.put(InitiationMethod.QR_STATIC_CODE, "11");
    statusCodes.put(InitiationMethod.QR_DYNAMIC_CODE, "12");
    statusCodes.put(InitiationMethod.BLE_STATIC_CODE, "21");
    statusCodes.put(InitiationMethod.BLE_DYNAMIC_CODE, "22");
    statusCodes.put(InitiationMethod.NFC_STATIC_CODE, "31");
    statusCodes.put(InitiationMethod.NFC_DYNAMIC_CODE, "32");
  }

  public static Map<InitiationMethod, String> getStatusCodes() {
    return statusCodes;
  }

  public static String getPointOfInitiationMethod(InitiationMethod code) {
    String pointOfInitiationMethod = statusCodes.get(code);
    return (pointOfInitiationMethod != null) ? pointOfInitiationMethod : QR_DYNAMIC_CODE;
  }

}
