package com.visa.service.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.visa.service.visa.model.response.CreateAliasResponse;
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
public class ApiCreateAliasResponse {

  private String guid;
  private String message;
  private String reason;

  public static ApiCreateAliasResponse fromCreateAliasResponse(CreateAliasResponse response) {
    return ApiCreateAliasResponse.builder()
        .guid(response.getGuid())
        .build();
  }

}
