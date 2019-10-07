package com.visa.service.visa.model.request;

import lombok.*;

/**
 * @author Abdussamad
 */
@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class MerchandiseReturnMerchantVerificationValueRequest {

    private String mvvVisaAssigned;
    private String mvvAcquirerAssigned;
}
