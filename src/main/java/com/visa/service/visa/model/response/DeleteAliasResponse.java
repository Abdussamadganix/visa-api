package com.visa.service.visa.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
public class DeleteAliasResponse {

  private String guid;
  private String message;
  private ResponseStatus responseStatus;
  private String reason;
}
