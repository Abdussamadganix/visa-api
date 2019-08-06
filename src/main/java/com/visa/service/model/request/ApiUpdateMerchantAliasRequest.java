package com.visa.service.model.request;

import com.visa.service.model.constant.InitiationMethod;
import com.visa.service.model.constant.PointOfInitiationMethod;
import com.visa.service.model.entity.MerchantAlias;
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
  private String terminalId;

  public MerchantAlias toMerchantAlias() {
    MerchantAlias merchantAlias = new MerchantAlias();
    merchantAlias.setAliasId(aliasId);
    merchantAlias.setIsAgentAlias(isAgentAlias);
    merchantAlias.setMerchantId(merchantId);
    merchantAlias.setMerchantCategoryCode(merchantCategoryCode);
    merchantAlias.setPayloadFormatIndicator(payloadFormatIndicator);
    merchantAlias.setPointOfInitiationMethod(String.valueOf(pointOfInitiationMethod));
    merchantAlias.setTransactionCurrencyCode(transactionCurrencyCode);
    merchantAlias.setTipConvenienceFeeIndicator(tipConvenienceFeeIndicator);
    merchantAlias.setConvenienceFeeAmount(convenienceFeeAmount);
    merchantAlias.setConvenienceFeePercentage(convenienceFeePercentage);
    merchantAlias.setRecipientName(recipientName);
    merchantAlias.setCity(city);
    merchantAlias.setCountry(country);
    merchantAlias.setTerminalId(terminalId);
    return merchantAlias;
  }

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
