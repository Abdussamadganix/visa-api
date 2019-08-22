package com.visa.service.visa.model.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.visa.service.model.entity.MerchantAlias;
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
public class DeleteMerchantAliasRequest {

  private String aliasId;
  private String isAgentAlias;
  private String merchantId;

  public MerchantAlias toMerchantAlias() {
    MerchantAlias merchantAlias = new MerchantAlias();
    merchantAlias.setAliasId(aliasId);
    merchantAlias.setIsAgentAlias(isAgentAlias);
    merchantAlias.setMerchantId(isAgentAlias);
    return merchantAlias;
  }

}
