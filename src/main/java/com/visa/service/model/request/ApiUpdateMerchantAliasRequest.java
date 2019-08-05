package com.visa.service.model.request;

import com.visa.service.model.constant.InitiationMethod;
import com.visa.service.model.constant.PointOfInitiationMethod;
import com.visa.service.visa.model.request.UpdateMerchantAliasRequest;
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
public class ApiUpdateMerchantAliasRequest {

  private String aliasId;
  private String isAgentAlias;
  private String merchantId;
  private String merchantCategoryCode;
  private String payloadFormatIndicator;
  private InitiationMethod pointOfInitiationMethod;
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

  public UpdateMerchantAliasRequest toUpdateMerchantAliasRequest() {
    return UpdateMerchantAliasRequest.builder()
        .aliasId(aliasId)
        .isAgentAlias(isAgentAlias)
        .merchantId(merchantId)
        .merchantCategoryCode(merchantCategoryCode)
        .payloadFormatIndicator(payloadFormatIndicator)
        .pointOfInitiationMethod(
            PointOfInitiationMethod.getPointOfInitiationMethod(pointOfInitiationMethod))
        .transactionCurrencyCode(transactionCurrencyCode)
        .tipConvenienceFeeIndicator(tipConvenienceFeeIndicator)
        .convenienceFeeAmount(convenienceFeeAmount)
        .convenienceFeePercentage(convenienceFeePercentage)
        .recipientName(recipientName)
        .city(city)
        .country(country)
        .postalCode(postalCode)
        .email(email)
        .phone(phone)
        .build();
  }


}
