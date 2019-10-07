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
public class MerchandiseReturnTransactionSettlementFlagsResponse {

    private String settlementResponsibilityFlag;
    private String givPreviouslyUpdatedFlag;
    private String givUpdatedFlag;
    private String settlementServiceFlag;

}
