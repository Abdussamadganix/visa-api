package com.visa.service.visa.model.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by abdussamad.olaiya on 23/06/2019.
 */
public class CardType {

  private static Map<String, String> aliasType = new HashMap<>();

  private static String DEFAULT_TYPE = "01";

  static {
    aliasType.put("MASTERCARD", "MasterCard");
    aliasType.put("VISA_PLATINUM", "Visa Platinum");
    aliasType.put("VISA_REWARDS", "Visa Rewards");
    aliasType.put("VISA_SELECT", "Visa Select");
    aliasType.put("VISA_GOLD", "Visa Gold");
    aliasType.put("PRIVATE_LABEL", "Private Label");
    aliasType.put("PRIVATE_LABEL_PREPAID", "Private Label Prepaid");
    aliasType.put("PRIVATE_LABEL_BASIC", "Private Label Basic");
    aliasType.put("PRIVATE_LABEL_STANDARD", "Private Label Standard");
    aliasType.put("PRIVATE_LABEL_ENHANCED", "Private Label Enhanced");
    aliasType.put("PRIVATE_LABEL_SPECIALIZED", "Private Label Specialized");
    aliasType.put("PRIVATE_LABEL_PREMIUM", "Private Label Premium");
    aliasType.put("PROPRIETARY", "Proprietary");
    aliasType.put("VISA_PURCHASING", "Visa Purchasing");
    aliasType.put("VISA_PURCHASING_WITH_FLEET", "Visa Purchasing with Fleet");
    aliasType.put("VISA_GOVERNMENT_PURCHASING", "Visa Government Purchasing");
    aliasType.put("VISA_GOVERNMENT_PURCHASING_WITH_FLEET", "Visa Government Purchasing with Fleet");
    aliasType.put("VISA_COMMERCIAL_AGRICULTURE", "Visa Commercial Agriculture");
    aliasType.put("VISA_COMMERCIAL_TRANSPORT", "Visa Commercial Transport");
    aliasType.put("VISA_COMMERCIAL_MARKETPLACE", "Visa Commercial Marketplace");
    aliasType.put("VISA_TRAVEL_MONEY", "Visa Travel Money");
    aliasType.put("VISA_V_PAY", "Visa V PAY");
    aliasType.put("VISA_ELECTRON", "Visa Electron");
    aliasType.put("VISA_TRADITIONAL", "Visa Traditional");
    aliasType.put("AMERICAN_EXPRESS", "American Express");
    aliasType.put("VISA_TRADITIONAL_REWARDS", "Visa Traditional Rewards");
    aliasType.put("VISA_SIGNATURE", "Visa Signature");
    aliasType.put("VISA_SIGNATURE_PREFERRED", "Visa Signature Preferred");
    aliasType.put("DISCOVER", "Discover");
    aliasType.put("DINERS", "Diners");
    aliasType.put("PROPRIETARY_ATM", "Proprietary ATM");
    aliasType.put("VISA_CLASSIC", "Visa Classic");
    aliasType.put("VISA_BUSINESS", "Visa Business");
    aliasType.put("VISA_SIGNATURE_BUSINESS", "Visa Signature Business");
    aliasType.put("VISA_BUSINESS_CHECK_CARD", "Visa Business Check Card");
    aliasType.put("VISA_BUSINESS_ENHANCED", "Visa Business Enhanced");
    aliasType.put("VISA_INFINITE_BUSINESS", "Visa Infinite Business");
    aliasType.put("VISA_BUSINESS_REWARDS", "Visa Business Rewards");
    aliasType.put("VISA_INFINITE", "Visa Infinite");
    aliasType.put("VISA_INFINITE_PRIVILEGE", "Visa Infinite Privilege");
    aliasType.put("VISA_UHNW", "Visa UHNW");
    aliasType.put("VISA_HEALTHCARE", "Visa Healthcare");
    aliasType.put("JCB", "JCB");
    aliasType.put("VISA_CORPORATE_T_AND_E", "Visa Corporate T&E");

  }

  public static Map<String, String> getCardType() {
    return aliasType;
  }

  public static String getCardType(String code) {
    String errorCode = aliasType.get(code);
    return (errorCode != null) ? errorCode : DEFAULT_TYPE;
  }

}
