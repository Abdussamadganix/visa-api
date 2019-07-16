package com.visa.service.model.request;

import com.visa.service.exception.BadRequestException;
import com.visa.service.model.constant.AliasType;
import com.visa.service.model.constant.CardType;
import com.visa.service.model.constant.PaymentType;
import com.visa.service.visa.model.request.CreateAliasItemRequest;
import com.visa.service.visa.model.request.CreateAliasRequest;
import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
public class ApiCreateAliasRequest {

  @NotNull
  @NotEmpty
  private String guid;

  @NotNull
  @NotEmpty
  private String recipientFirstName;

  @NotNull
  @NotEmpty
  private String recipientMiddleName;

  @NotNull
  @NotEmpty
  private String recipientLastName;

  private String address1;
  private String address2;

  private String city;

  @NotNull
  @NotEmpty
  private String country;

  private String postalCode;

  @NotNull
  @NotEmpty
  private String consentDateTime;

  @NotNull
  @NotEmpty
  private String recipientPrimaryAccountNumber;

  @NotNull
  @NotEmpty
  private String issuerName;

  private CardType cardType;

  @NotNull
  @NotEmpty
  private String alias;

  private AliasType aliasType;

  private Boolean isDefault;
  private PaymentType paymentType;
  private String expiryDate;
  private String contactEmail;
  private String contactPhoneNumber;

  public CreateAliasRequest toCreateAliasRequest() {
    CreateAliasRequest createAliasRequest = new CreateAliasRequest();
//    CreateAliasItemRequest createAliasRequest = new CreateAliasItemRequest();
    createAliasRequest.setGuid(guid);
    createAliasRequest.setRecipientFirstName(recipientFirstName);
    createAliasRequest.setRecipientLastName(recipientLastName);
    createAliasRequest.setRecipientMiddleName(recipientMiddleName);
    createAliasRequest.setRecipientPrimaryAccountNumber(recipientPrimaryAccountNumber);
    createAliasRequest.setConsentDateTime(consentDateTime);
    createAliasRequest.setCity(city);
    createAliasRequest.setCountry(country);
    createAliasRequest.setPostalCode(postalCode);
    createAliasRequest.setIssuerName(issuerName);
    if (cardType == null) {
      throw new BadRequestException("cardType may not be null");
    }
    createAliasRequest.setCardType(
        com.visa.service.visa.model.constant.CardType.getCardType(String.valueOf(cardType)));
    createAliasRequest.setAddress1(address1);
    createAliasRequest.setAddress2(address2);
    createAliasRequest.setAlias(alias);
    if (aliasType == null) {
      throw new BadRequestException("aliasType may not be null");
    }
    createAliasRequest.setAliasType(
        com.visa.service.visa.model.constant.AliasType.getAliasType(String.valueOf(aliasType)));
    createAliasRequest.setPaymentType((paymentType != null) ? String.valueOf(paymentType) : "");
    createAliasRequest.setContactEmail(contactEmail);
    createAliasRequest.setContactPhoneNumber(contactPhoneNumber);
    createAliasRequest.setExpiryDate(expiryDate);
    createAliasRequest.setIsDefault(String.valueOf(isDefault));
//    createAlias.setCreateAliasRequestPayload(createAliasRequest);
    return createAliasRequest;
  }
}
