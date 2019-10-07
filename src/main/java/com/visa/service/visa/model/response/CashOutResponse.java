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
public class CashOutResponse {

    private String approvalCode;
    private String transactionIdentifier;
    private String statusIdentifier;
    private String retrievalReferenceNumber;
    private String responseCode;
    private String actionCode;
    private String transactionDateTime;
    private String status;
    private String statusMessage;
    private String message;
    private String reason;
    private ResponseStatus responseStatus;
    private String errorMessage;
}
