package com.visa.service.visa.model.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * @author Abdussamad
 */
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CashInRequest {

    @NotNull
    @NotEmpty
    private String localTransactionDateTime;
    @NotNull
    @NotEmpty
    private String acquiringBin;
    @NotNull
    @NotEmpty
    private String businessApplicationId;
    @NotNull
    @NotEmpty
    private String merchantCategoryCode;
    @NotNull
    @NotEmpty
    private String acquirerCountryCode;
    @NotNull
    @NotEmpty
    private String retrievalReferenceNumber;
    @NotNull
    @NotEmpty
    private String amount;
    private String senderReference;
    @NotNull
    @NotEmpty
    private String recipientPrimaryAccountNumber;
    @NotNull
    @NotEmpty
    private String systemsTraceAuditNumber;
    @NotNull
    @NotEmpty
    private String senderAccountNumber;
    @NotNull
    @NotEmpty
    private String transactionCurrencyCode;
    @NotNull
    @NotEmpty
    private String senderName;

    private CashInCardAcceptorRequest cardAcceptor;
}
