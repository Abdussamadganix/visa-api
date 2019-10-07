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

  public static FailedResponse fromResponseStatus(String message, String reason) {
    return FailedResponse.builder()
        .message(message)
        .reason(reason)
        .build();
  }

    public static FailedResponse fromErrorMessage(String errorMessage) {
        return FailedResponse.builder()
                .message(errorMessage)
                .build();
    }

}
