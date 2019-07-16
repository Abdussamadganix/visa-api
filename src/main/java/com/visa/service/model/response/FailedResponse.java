package com.visa.service.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.visa.service.visa.model.response.ResponseStatus;
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
public class FailedResponse {

  private String message;
  private String reason;

  public static FailedResponse fromResponseStatus(ResponseStatus responseStatus) {
    return FailedResponse.builder()
        .message(responseStatus.getMessage())
        .reason(responseStatus.getReason())
        .build();
  }

}
