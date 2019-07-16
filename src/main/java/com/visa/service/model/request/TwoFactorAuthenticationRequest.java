package com.visa.service.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @author Abdussamad
 */
@AllArgsConstructor
@Builder
@Data
//@NoArgsConstructor
public class TwoFactorAuthenticationRequest {

  private String token;
  private String handle;
}
