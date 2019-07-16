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
public class UpdateAliasRequest {

  private String guid;
  private String newGuid;
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
}
