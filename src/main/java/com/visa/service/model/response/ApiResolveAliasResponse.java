package com.visa.service.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.visa.service.visa.model.response.ResolveAliasResponse;
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
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ApiResolveAliasResponse {

  private String recipientPrimaryAccountNumber;
  private String recipientName;
  private String issuerName;
  private String cardType;
  private String city;
  private String country;
  private String postalCode;

  public static ApiResolveAliasResponse formResolveAliasResponse(ResolveAliasResponse response) {
    return ApiResolveAliasResponse.builder()
        .recipientPrimaryAccountNumber(response.getRecipientPrimaryAccountNumber())
        .recipientName(response.getRecipientName())
        .issuerName(response.getIssuerName())
        .cardType(response.getCardType())
        .city(response.getCity())
        .country(response.getCountry())
        .postalCode(response.getPostalCode())
        .build();
  }
}
