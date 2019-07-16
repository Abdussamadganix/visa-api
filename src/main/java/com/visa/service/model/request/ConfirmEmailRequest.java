package com.visa.service.model.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Abdussamad
 */
@Data
@EqualsAndHashCode()
public class ConfirmEmailRequest extends TwoFactoredRequest {

  private String email;
}
