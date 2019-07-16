package com.visa.service.visa.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
public class ResolveAliasResponse {

  private String recipientPrimaryAccountNumber;
  private String recipientName;
  private String issuerName;
  private String cardType;
  private String city;
  private String country;
  private String postalCode;
  private ResponseStatus responseStatus;
}
