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
public class MerchandiseReturnTransactionResponse {

    private String transactionIdentifier;
    private String statusIdentifier;
    private String actionCode;
    private String approvalCode;
    private String transmissionDateTime;
    private String responseCode;
    private String feeProgramIndicator;
    private String retrievalReferenceNumber;
    private MerchandiseReturnTransactionCardAcceptorResponse cardAcceptor;
    private MerchandiseReturnTransactionSettlementFlagsResponse settlementFlags;
    private String message;
    private String reason;
    private ResponseStatus responseStatus;
    private String errorMessage;

}
