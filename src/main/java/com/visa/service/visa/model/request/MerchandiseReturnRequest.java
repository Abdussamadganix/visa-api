package com.visa.service.visa.model.request;

import lombok.*;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * @author Abdussamad
 */
@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class MerchandiseReturnRequest {

    @NotNull
    @NotEmpty
    private String systemsTraceAuditNumber;
    @NotNull
    @NotEmpty
    private String retrievalReferenceNumber;
    @NotNull
    @NotEmpty
    private String localTransactionDateTime;
    @NotNull
    @NotEmpty
    private String acquiringBin;
    @NotNull
    @NotEmpty
    private String acquirerCountryCode;
    @NotNull
    @NotEmpty
    private String transactionCurrencyCode;
    @NotNull
    @NotEmpty
    private String recipientPrimaryAccountNumber;
    @NotNull
    @NotEmpty
    private String amount;
    @NotNull
    @NotEmpty
    private String merchantCategoryCode;
    private String feeProgramIndicator;
    private String transactionFeeAmt;
    private String transactionIdentifier;
    private String cardExpiryDate;
    private String settlementServiceIndicator;
    private MerchandiseReturnCardAcceptorRequest cardAcceptor;
    private Object merchantVerificationValue;
}
