package com.visa.service.visa.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Abdussamad
 */
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Data
public class UpdateMerchantAliasRequest {

  private String aliasId;
  private String isAgentAlias;
  private String merchantId;
  private String merchantCategoryCode;
  private String payloadFormatIndicator;
  private String pointOfInitiationMethod;
  private String transactionCurrencyCode;
  private String tipConvenienceFeeIndicator;
  private String convenienceFeeAmount;
  private String convenienceFeePercentage;
  private String recipientName;
  private String city;
  private String country;
  private String postalCode;
  private String email;
  private String phone;
}
