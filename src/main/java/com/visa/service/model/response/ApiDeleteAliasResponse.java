package com.visa.service.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.visa.service.visa.model.request.DeleteAliasRequest;
import com.visa.service.visa.model.response.DeleteAliasResponse;
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
public class ApiDeleteAliasResponse {

  private String guid;

  public static ApiDeleteAliasResponse fromDeleteAliasResponse(DeleteAliasResponse response) {
    return ApiDeleteAliasResponse.builder()
        .guid(response.getGuid())
        .build();
  }
}
