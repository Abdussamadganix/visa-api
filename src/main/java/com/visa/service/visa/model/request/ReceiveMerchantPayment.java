package com.visa.service.visa.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by abdussamad.olaiya on 24/06/2019.
 */
@AllArgsConstructor
@Builder
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ReceiveMerchantPayment {

  private String encMerchantPAN;
  private String transactionAmount;
  private String transmissionDateTime;
  private String localTransactionTime;
  private String localTransactionDate;
  private String originatorCountryCode;
  private String originatorBIN;
  private String retrievalReferenceNumber;
  private String systemTraceAuditNumber;
  private String transactionCurrencyCode;
  private String visaTransactionId;
  private String encConsumerPAN;
  private String encConsumerName;
  private String decimalPositionIndicator;
  private String transactionFeeAmount;
  private String velocityLimitIndicator;
  private String billIdFormat;
  private String billId;
  private String refId;
  private String otherPhoneNumber;
  private String otherEmailAddress;

}
