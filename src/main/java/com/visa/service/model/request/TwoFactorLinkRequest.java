package com.visa.service.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @author Abdussamad
 */
@AllArgsConstructor
@Data
@Builder
//@NoArgsConstructor
public class TwoFactorLinkRequest {

  private String email;
  private String redirectUrl;
//  private RequestType requestType;
}
