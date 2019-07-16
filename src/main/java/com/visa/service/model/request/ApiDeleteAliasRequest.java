package com.visa.service.model.request;

import com.visa.service.visa.model.request.DeleteAliasRequest;
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
public class ApiDeleteAliasRequest {

  private String guid;
  private String alias;

  public DeleteAliasRequest toDeleteAliasRequest() {
    DeleteAliasRequest deleteAliasRequest = new DeleteAliasRequest();
    deleteAliasRequest.setAlias(alias);
    deleteAliasRequest.setGuid(guid);
    return deleteAliasRequest;
  }

}
