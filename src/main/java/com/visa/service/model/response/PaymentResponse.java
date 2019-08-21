package com.visa.service.model.response;

import com.visa.service.model.constant.Status;
import com.visa.service.model.entity.Payment;
import com.visa.service.util.TimeUtil;
import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Abdussamad
 */
@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class PaymentResponse {

  private String uniqueKey;
  private String merchantId;
  private String amount;
  private String currency;
  private String terminalId;
  private String billId;
  private String encMerchantPan;
  private String encConsumerName;
  private String transactionFeeAmount;
  private String billIdFormat;
  private String refId;
  private String otherPhoneNumber;
  private String otherEmailAddress;
  private String messageType;
  private String authIdResponse;
  private String responseCode;
  private String rejectionCode;
  private String encConsumerPan;
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
  private String createdAt;
  private String updatedAt;
  private Status status;

  public static PaymentResponse fromPayment(Payment payment) {
    return PaymentResponse.builder()
        .uniqueKey(payment.getUniqueKey())
        .merchantId(payment.getMerchantId())
        .amount(payment.getAmount())
        .currency(payment.getCurrency())
        .terminalId(payment.getCurrency())
        .billId(payment.getBillId())
        .encMerchantPan(payment.getEncMerchantPan())
        .encConsumerName(payment.getEncConsumerName())
        .transactionFeeAmount(payment.getTransactionFeeAmount())
        .billIdFormat(payment.getBillIdFormat())
        .refId(payment.getRefId())
        .otherPhoneNumber(payment.getOtherPhoneNumber())
        .otherEmailAddress(payment.getOtherEmailAddress())
        .messageType(payment.getMessageType())
        .authIdResponse(payment.getAuthIdResponse())
        .responseCode(payment.getResponseCode())
        .rejectionCode(payment.getRejectionCode())
        .encConsumerPan(payment.getEncConsumerPan())
        .transactionAmount(payment.getTransactionAmount())
        .transmissionDateTime(payment.getTransmissionDateTime())
        .systemTraceAuditNumber(payment.getSystemTraceAuditNumber())
        .localTransactionTime(payment.getLocalTransactionTime())
        .localTransactionDate(payment.getLocalTransactionDate())
        .originatorCountryCode(payment.getOriginatorCountryCode())
        .originatorBIN(payment.getOriginatorBIN())
        .retrievalReferenceNumber(payment.getRetrievalReferenceNumber())
        .transactionCurrencyCode(payment.getTransactionCurrencyCode())
        .visaTransactionId(payment.getVisaTransactionId())
        .decimalPositionIndicator(payment.getDecimalPositionIndicator())
        .encAgentName(payment.getEncAgentName())
        .agentCity(payment.getAgentCity())
        .velocityLimitIndicator(payment.getVelocityLimitIndicator())
        .createdAt(TimeUtil.getIsoTime(payment.getCreatedAt()))
        .updatedAt(TimeUtil.getIsoTime(payment.getUpdatedAt()))
        .status(payment.getStatus())
        .build();
  }


}
