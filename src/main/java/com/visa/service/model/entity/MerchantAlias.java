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
@Table(name = "merchant_alias")
public class MerchantAlias {

  private Integer id;
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
  private Timestamp createdAt;
  private Timestamp updatedAt;
  private String terminalId;
  private Status createStatus;
  private Status updateStatus;
  private Status deleteStatus;
  private String errorReason;
  private String postalCode;
  private String email;
  private String phone;
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
  @Column(name = "alias_id")
  public String getAliasId() {
    return aliasId;
  }

  public void setAliasId(String aliasId) {
    this.aliasId = aliasId;
  }

  @Basic
  @Column(name = "is_agent_alias")
  public String getIsAgentAlias() {
    return isAgentAlias;
  }

  public void setIsAgentAlias(String isAgentAlias) {
    this.isAgentAlias = isAgentAlias;
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
  @Column(name = "merchant_category_code")
  public String getMerchantCategoryCode() {
    return merchantCategoryCode;
  }

  public void setMerchantCategoryCode(String merchantCategoryCode) {
    this.merchantCategoryCode = merchantCategoryCode;
  }

  @Basic
  @Column(name = "payload_format_indicator")
  public String getPayloadFormatIndicator() {
    return payloadFormatIndicator;
  }

  public void setPayloadFormatIndicator(String payloadFormatIndicator) {
    this.payloadFormatIndicator = payloadFormatIndicator;
  }

  @Basic
  @Column(name = "point_of_initiation_method")
  public String getPointOfInitiationMethod() {
    return pointOfInitiationMethod;
  }

  public void setPointOfInitiationMethod(String pointOfInitiationMethod) {
    this.pointOfInitiationMethod = pointOfInitiationMethod;
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
  @Column(name = "tip_convenience_fee_indicator")
  public String getTipConvenienceFeeIndicator() {
    return tipConvenienceFeeIndicator;
  }

  public void setTipConvenienceFeeIndicator(String tipConvenienceFeeIndicator) {
    this.tipConvenienceFeeIndicator = tipConvenienceFeeIndicator;
  }

  @Basic
  @Column(name = "convenience_fee_amount")
  public String getConvenienceFeeAmount() {
    return convenienceFeeAmount;
  }

  public void setConvenienceFeeAmount(String convenienceFeeAmount) {
    this.convenienceFeeAmount = convenienceFeeAmount;
  }

  @Basic
  @Column(name = "convenience_fee_percentage")
  public String getConvenienceFeePercentage() {
    return convenienceFeePercentage;
  }

  public void setConvenienceFeePercentage(String convenienceFeePercentage) {
    this.convenienceFeePercentage = convenienceFeePercentage;
  }

  @Basic
  @Column(name = "recipient_name")
  public String getRecipientName() {
    return recipientName;
  }

  public void setRecipientName(String recipientName) {
    this.recipientName = recipientName;
  }

  @Basic
  @Column(name = "city")
  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  @Basic
  @Column(name = "country")
  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
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
  @Column(name = "error_reason")
  public String getErrorReason() {
    return errorReason;
  }

  public void setErrorReason(String errorReason) {
    this.errorReason = errorReason;
  }


  @Basic
  @Column(name = "create_status")
  @Enumerated(EnumType.STRING)
  public Status getCreateStatus() {
    return createStatus;
  }

  public void setCreateStatus(Status createStatus) {
    this.createStatus = createStatus;
  }

  @Basic
  @Column(name = "update_status")
  @Enumerated(EnumType.STRING)
  public Status getUpdateStatus() {
    return updateStatus;
  }

  public void setUpdateStatus(Status updateStatus) {
    this.updateStatus = updateStatus;
  }

  @Basic
  @Column(name = "delete_status")
  @Enumerated(EnumType.STRING)
  public Status getDeleteStatus() {
    return deleteStatus;
  }

  public void setDeleteStatus(Status deleteStatus) {
    this.deleteStatus = deleteStatus;
  }

  @Basic
  @Column(name = "postal_code")
  public String getPostalCode() {
    return postalCode;
  }

  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }

  @Basic
  @Column(name = "email")
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Basic
  @Column(name = "phone")
  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  @Basic
  @Column(name = "status", nullable = true)
  @Enumerated(EnumType.STRING)
  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

}
