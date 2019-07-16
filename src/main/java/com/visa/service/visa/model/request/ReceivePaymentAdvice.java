package com.visa.service.visa.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by abdussamad.olaiya on 25/06/2019.
 */
@AllArgsConstructor
@Builder
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ReceivePaymentAdvice {

  private String messageType;
  private String authIdResponse;
  private String responseCode;
  private String rejectionCode;
  private String encConsumerPAN;
  private String transactionAmount;
  private String transmissionDateTime;
  private String systemTraceAuditNumber;
  private String localTransactionTime;
  private String localTransactionDate;
  private String originatorCountryCode;
  private String originatorBIN;
  private String retrievalReferenceNumber;
  private String transactionCurrencyCode;
  private String visaTransactionId;
  private String decimalPositionIndicator;
  private String encAgentName;
  private String agentCity;
  private String velocityLimitIndicator;

}
