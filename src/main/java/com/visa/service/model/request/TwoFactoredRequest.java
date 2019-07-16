package com.visa.service.model.request;

import lombok.Data;

/**
 * @author Abdussamad
 */
@Data
public abstract class TwoFactoredRequest {

  private TwoFactorAuthenticationRequest twoFactorAuthentication;
}
