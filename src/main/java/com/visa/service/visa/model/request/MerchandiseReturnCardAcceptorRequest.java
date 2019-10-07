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
public class MerchandiseReturnCardAcceptorRequest {

    private String name;
    private String terminalId;
    private String idCode;
    private MerchandiseReturnData address;
}
