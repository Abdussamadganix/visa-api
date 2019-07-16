package com.visa.service.model.request;

import com.visa.service.visa.model.request.GetAliasRequest;
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
@Data
public class ApiGetAliasRequest {

  private String guid;

  public GetAliasRequest toGetAliasRequest() {
    return GetAliasRequest.builder()
        .guid(guid)
        .build();
  }

}
