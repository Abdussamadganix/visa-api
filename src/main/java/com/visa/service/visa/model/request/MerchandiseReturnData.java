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
public class MerchandiseReturnData {

    private String city;
    private String country;
    private String county;
    private String zipCode;
    private String state;

}
