package com.visa.service.visa.model.response;

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
public class GetAliasResponse {

  private String guid;
  private String recipientFirstName;
  private String recipientMiddleName;
  private String recipientLastName;
  private String address1;
  private String address2;
  private String city;
  private String country;
  private String postalCode;
  private String consentDateTime;
  private String recipientPrimaryAccountNumber;
  private String issuerName;
  private String cardType;
  private String alias;
  private String aliasType;
  private String isDefault;
  private String paymentType;
  private String expiryDate;
  private String contactEmail;
  private String contactPhoneNumber;
  private String status;
  private String statusChangeDateTime;
  private Object aliases;
  private String message;
  private String reason;
  private ResponseStatus responseStatus;

}
