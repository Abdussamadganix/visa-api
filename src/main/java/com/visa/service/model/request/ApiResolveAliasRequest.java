package com.visa.service.model.request;

import com.visa.service.exception.BadRequestException;
import com.visa.service.model.constant.BusinessApplicationType;
import com.visa.service.visa.model.constant.BusinessApplicationId;
import com.visa.service.visa.model.request.ResolveAliasRequest;
import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
public class ApiResolveAliasRequest {

  @NotNull
  @NotEmpty
  private String alias;
  private BusinessApplicationType businessApplicationId;
  private String accountLookUp;

  public ResolveAliasRequest toResolveAliasRequest() {
    ResolveAliasRequest resolveAliasRequest = new ResolveAliasRequest();
    resolveAliasRequest.setAlias(alias);
    if (businessApplicationId == null) {
      throw new BadRequestException("businessApplicationId may not be null");
    }
    resolveAliasRequest.setBusinessApplicationId(
        BusinessApplicationId.geBusinessApplicationType(String.valueOf(businessApplicationId)));
    resolveAliasRequest.setAccountLookUp(accountLookUp);
    return resolveAliasRequest;
  }

}
