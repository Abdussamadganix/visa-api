package com.visa.service.model.request;

import com.visa.service.model.constant.AliasType;
import com.visa.service.model.constant.CardType;
import com.visa.service.model.constant.PaymentType;
import com.visa.service.visa.model.request.UpdateAliasRequest;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author Abdussamad
 */
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Data
public class ApiUpdateAliasRequest {

  @NotNull
  @NotEmpty
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

  private CardType cardType;

  private String alias;

  private AliasType aliasType;

  private Boolean isDefault;
  private PaymentType paymentType;
  private String expiryDate;
  private String contactEmail;
  private String contactPhoneNumber;

  public UpdateAliasRequest toUpdateAliasRequest() {
    UpdateAliasRequest updateAliasRequest = new UpdateAliasRequest();
    updateAliasRequest.setGuid(guid);
    updateAliasRequest.setNewGuid(newGuid);
    updateAliasRequest.setRecipientFirstName(recipientFirstName);
    updateAliasRequest.setRecipientLastName(recipientLastName);
    updateAliasRequest.setRecipientMiddleName(recipientMiddleName);
    updateAliasRequest.setRecipientPrimaryAccountNumber(recipientPrimaryAccountNumber);
    updateAliasRequest.setConsentDateTime(consentDateTime);
    updateAliasRequest.setCity(city);
    updateAliasRequest.setCountry(country);
    updateAliasRequest.setPostalCode(postalCode);
    updateAliasRequest.setIssuerName(issuerName);
    updateAliasRequest.setCardType(
        (cardType != null) ? com.visa.service.visa.model.constant.CardType
            .getCardType(String.valueOf(cardType)) : null);
    updateAliasRequest.setAddress1(address1);
    updateAliasRequest.setAddress2(address2);
    updateAliasRequest.setAlias(alias);
    updateAliasRequest.setAliasType(
        (aliasType != null) ? com.visa.service.visa.model.constant.AliasType
            .getAliasType(String.valueOf(aliasType)) : null);
    updateAliasRequest.setPaymentType((paymentType != null) ? String.valueOf(paymentType) : null);
    updateAliasRequest.setContactEmail(contactEmail);
    updateAliasRequest.setContactPhoneNumber(contactPhoneNumber);
    updateAliasRequest.setExpiryDate(expiryDate);
    updateAliasRequest.setIsDefault(String.valueOf(isDefault));
    return updateAliasRequest;
  }
}
