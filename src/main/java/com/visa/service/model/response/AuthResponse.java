package com.visa.service.model.response;

import com.visa.service.model.entity.Token;
import com.visa.service.util.TimeUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Abdussamad
 */
@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class AuthResponse {

  private String token;
  private String expires;
  private String tokenType;

  public static AuthResponse fromToken(Token token) {
    return AuthResponse.builder()
        .token(token.getToken())
        .expires(TimeUtil.getIsoTime(token.getExpiresAt()))
        .tokenType("bearer")
        .build();
  }

}
