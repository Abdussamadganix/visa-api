package com.visa.service.visa.model.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by abdussamad.olaiya on 24/06/2019.
 */
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetMerchantAliasRequest {

  @JsonProperty("merchantaliasid")
  private String merchantAliasId;
  private String type;

}
