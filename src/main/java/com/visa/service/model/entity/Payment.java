package com.visa.service.model.entity;

import com.visa.service.model.constant.Status;
import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

/**
 * @author Abdussamad
 */
@Entity
@Table(name = "payment")
public class Payment {

  private Integer id;
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
  private Timestamp createdAt;
  private Timestamp updatedAt;
  private Status status;


  @Id
  @Column(name = "id", nullable = false)
  @GeneratedValue
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  @Basic
  @Column(name = "unique_key", nullable = false, length = 50)
  public String getUniqueKey() {
    return uniqueKey;
  }

  public void setUniqueKey(String uniqueKey) {
    this.uniqueKey = uniqueKey;
  }

  @Basic
  @Column(name = "merchant_id")
  public String getMerchantId() {
    return merchantId;
  }

  public void setMerchantId(String merchantId) {
    this.merchantId = merchantId;
  }

  @Basic
  @Column(name = "amount")
  public String getAmount() {
    return amount;
  }

  public void setAmount(String amount) {
    this.amount = amount;
  }

  @Basic
  @Column(name = "currency")
  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  @Basic
  @Column(name = "terminal_id")
  public String getTerminalId() {
    return terminalId;
  }

  public void setTerminalId(String terminalId) {
    this.terminalId = terminalId;
  }

  @Basic
  @Column(name = "bill_id")
  public String getBillId() {
    return billId;
  }

  public void setBillId(String billId) {
    this.billId = billId;
  }

  @Basic
  @Column(name = "enc_merchant_pan")
  public String getEncMerchantPan() {
    return encMerchantPan;
  }

  public void setEncMerchantPan(String encMerchantPAN) {
    this.encMerchantPan = encMerchantPAN;
  }

  @Basic
  @Column(name = "enc_consumer_name")
  public String getEncConsumerName() {
    return encConsumerName;
  }

  public void setEncConsumerName(String encConsumerName) {
    this.encConsumerName = encConsumerName;
  }

  @Basic
  @Column(name = "transaction_fee_amount")
  public String getTransactionFeeAmount() {
    return transactionFeeAmount;
  }

  public void setTransactionFeeAmount(String transactionFeeAmount) {
    this.transactionFeeAmount = transactionFeeAmount;
  }

  @Basic
  @Column(name = "bill_id_format")
  public String getBillIdFormat() {
    return billIdFormat;
  }

  public void setBillIdFormat(String billIdFormat) {
    this.billIdFormat = billIdFormat;
  }

  @Basic
  @Column(name = "ref_id")
  public String getRefId() {
    return refId;
  }

  public void setRefId(String refId) {
    this.refId = refId;
  }

  @Basic
  @Column(name = "other_phone_number")
  public String getOtherPhoneNumber() {
    return otherPhoneNumber;
  }

  public void setOtherPhoneNumber(String otherPhoneNumber) {
    this.otherPhoneNumber = otherPhoneNumber;
  }

  @Basic
  @Column(name = "other_email_address")
  public String getOtherEmailAddress() {
    return otherEmailAddress;
  }

  public void setOtherEmailAddress(String otherEmailAddress) {
    this.otherEmailAddress = otherEmailAddress;
  }

  @Basic
  @Column(name = "message_type")
  public String getMessageType() {
    return messageType;
  }

  public void setMessageType(String messageType) {
    this.messageType = messageType;
  }

  @Basic
  @Column(name = "auth_id_response")
  public String getAuthIdResponse() {
    return authIdResponse;
  }

  public void setAuthIdResponse(String authIdResponse) {
    this.authIdResponse = authIdResponse;
  }

  @Basic
  @Column(name = "response_code")
  public String getResponseCode() {
    return responseCode;
  }

  public void setResponseCode(String responseCode) {
    this.responseCode = responseCode;
  }

  @Basic
  @Column(name = "rejection_code")
  public String getRejectionCode() {
    return rejectionCode;
  }

  public void setRejectionCode(String rejectionCode) {
    this.rejectionCode = rejectionCode;
  }

  @Basic
  @Column(name = "enc_consumer_pan")
  public String getEncConsumerPan() {
    return encConsumerPan;
  }

  public void setEncConsumerPan(String encConsumerPan) {
    this.encConsumerPan = encConsumerPan;
  }

  @Basic
  @Column(name = "transaction_amount")
  public String getTransactionAmount() {
    return transactionAmount;
  }

  public void setTransactionAmount(String transactionAmount) {
    this.transactionAmount = transactionAmount;
  }

  @Basic
  @Column(name = "transmission_date_time")
  public String getTransmissionDateTime() {
    return transmissionDateTime;
  }

  public void setTransmissionDateTime(String transmissionDateTime) {
    this.transmissionDateTime = transmissionDateTime;
  }

  @Basic
  @Column(name = "system_trace_audit_number")
  public String getSystemTraceAuditNumber() {
    return systemTraceAuditNumber;
  }

  public void setSystemTraceAuditNumber(String systemTraceAuditNumber) {
    this.systemTraceAuditNumber = systemTraceAuditNumber;
  }

  @Basic
  @Column(name = "local_transaction_time")
  public String getLocalTransactionTime() {
    return localTransactionTime;
  }

  public void setLocalTransactionTime(String localTransactionTime) {
    this.localTransactionTime = localTransactionTime;
  }

  @Basic
  @Column(name = "local_transaction_date")
  public String getLocalTransactionDate() {
    return localTransactionDate;
  }

  public void setLocalTransactionDate(String localTransactionDate) {
    this.localTransactionDate = localTransactionDate;
  }

  @Basic
  @Column(name = "originator_country_code")
  public String getOriginatorCountryCode() {
    return originatorCountryCode;
  }

  public void setOriginatorCountryCode(String originatorCountryCode) {
    this.originatorCountryCode = originatorCountryCode;
  }

  @Basic
  @Column(name = "originator_bin")
  public String getOriginatorBIN() {
    return originatorBIN;
  }

  public void setOriginatorBIN(String originatorBIN) {
    this.originatorBIN = originatorBIN;
  }

  @Basic
  @Column(name = "retrieval_reference_number")
  public String getRetrievalReferenceNumber() {
    return retrievalReferenceNumber;
  }

  public void setRetrievalReferenceNumber(String retrievalReferenceNumber) {
    this.retrievalReferenceNumber = retrievalReferenceNumber;
  }

  @Basic
  @Column(name = "transaction_currency_code")
  public String getTransactionCurrencyCode() {
    return transactionCurrencyCode;
  }

  public void setTransactionCurrencyCode(String transactionCurrencyCode) {
    this.transactionCurrencyCode = transactionCurrencyCode;
  }

  @Basic
  @Column(name = "visa_transaction_id")
  public String getVisaTransactionId() {
    return visaTransactionId;
  }

  public void setVisaTransactionId(String visaTransactionId) {
    this.visaTransactionId = visaTransactionId;
  }

  @Basic
  @Column(name = "decimal_postion_indicator")
  public String getDecimalPositionIndicator() {
    return decimalPositionIndicator;
  }

  public void setDecimalPositionIndicator(String decimalPositionIndicator) {
    this.decimalPositionIndicator = decimalPositionIndicator;
  }

  @Basic
  @Column(name = "enc_agent_name")
  public String getEncAgentName() {
    return encAgentName;
  }

  public void setEncAgentName(String encAgentName) {
    this.encAgentName = encAgentName;
  }

  @Basic
  @Column(name = "agent_city")
  public String getAgentCity() {
    return agentCity;
  }

  public void setAgentCity(String agentCity) {
    this.agentCity = agentCity;
  }

  @Basic
  @Column(name = "velocity_limit_indicator")
  public String getVelocityLimitIndicator() {
    return velocityLimitIndicator;
  }

  public void setVelocityLimitIndicator(String velocityLimitIndicator) {
    this.velocityLimitIndicator = velocityLimitIndicator;
  }

  @Basic
  @Column(name = "created_at", nullable = false)
  public Timestamp getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Timestamp createdAt) {
    this.createdAt = createdAt;
  }

  @Basic
  @Column(name = "updated_at", nullable = true)
  public Timestamp getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(Timestamp updatedAt) {
    this.updatedAt = updatedAt;
  }

  @PrePersist
  public void beforeSave() {
    this.createdAt = new Timestamp(new Date().getTime());
  }

  @PreUpdate
  private void beforeUpdate() {
    this.updatedAt = new Timestamp(new Date().getTime());
  }

  @Basic
  @Column(name = "status", nullable = false, length = 16)
  @Enumerated(value = EnumType.STRING)
  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }
}
