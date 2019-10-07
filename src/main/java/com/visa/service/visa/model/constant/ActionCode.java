package com.visa.service.visa.model.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Abdussamad
 */
public class ActionCode {

    private static Map<String, String> actionCode = new HashMap<>();

    private static String DEFAULT_TYPE = "System malfunction or certain field error conditions";

    static {
        actionCode.put("00", "Approved and completed successfully");
        actionCode.put("59", "Suspected fraud");
        actionCode.put("01", "Refer to card issuer  ");
        actionCode.put("61", "Exceeds approval amount limit");
        actionCode.put("02", "Refer to card issuer, special condition  ");
        actionCode.put("62", "Restricted card (card invalid in this region or country)");
        actionCode.put("03", "Invalid merchant  ");
        actionCode.put("63", "Security violation (source is not correct issuer)");
        actionCode.put("04", "Pick up card (no fraud)  ");
        actionCode.put("64", "Transaction does not fulfill AML requirement");
        actionCode.put("05", "Do not honor  ");
        actionCode.put("65", "Exceeds withdrawal frequency limit");
        actionCode.put("06", "Error  ");
        actionCode.put("75", "Allowable number of PIN entry tries exceeded");
        actionCode.put("07", "Pick up card, special condition (fraud account)  ");
        actionCode.put("76", "Unsolicited reversal");
        actionCode.put("10", "Partial approval  ");
        actionCode.put("79", "Already reversed (by Switch)");
        actionCode.put("11", "Approved (V.I.P)  ");
        actionCode.put("80", "No financial impact");
        actionCode.put("12", "Invalid transaction  ");
        actionCode.put("81", "Cryptographic error found in PIN");
        actionCode.put("13", "Invalid amount or currency conversion field overflow  ");
        actionCode.put("82", "Negative CAM, dCVV, iCVV, or CVV results");
        actionCode.put("14", "Invalid account number (no such number)  ");
        actionCode.put("85", "No reason to decline a request for address verification, CVV2 verification, or a credit voucher or merchandise return");
        actionCode.put("15", "No such issuer  ");
        actionCode.put("86", "Cannot verify PIN; for example, no PVV");
        actionCode.put("19", "Re-enter transaction  ");
        actionCode.put("89", "Ineligible to receive financial position information (GIV)");
        actionCode.put("21", "No action taken  ");
        actionCode.put("91", "Issuer or switch inoperative and STIP not applicable or not available for this transaction; Time-out when no stand-in; POS Check Service: Destination unavailable; Credit Voucher and Merchandise Return Authorizations: V.I.P. sent the transaction to the issuer, but the issuer was unavailable.");
        actionCode.put("25", "Unable to locate record in file  ");
        actionCode.put("92", "Financial institution or intermediate network facility cannot be found for routing (receiving institution ID is invalid)");
        actionCode.put("28", "File temporarily not available for update or inquiry  ");
        actionCode.put("93", "Transaction cannot be completed - violation of law");
        actionCode.put("39", "No credit account  ");
        actionCode.put("94", "Duplicate transmission. Transaction submitted containing values in tracing data fields that duplicate values in a previously submitted transaction. Note: This code is available in SMS raw data in case of duplicate transmission.");
        actionCode.put("41", "Lost card, pick up (fraud account)  ");
        actionCode.put("96", "System malfunction or certain field error conditions");
        actionCode.put("43", "Stolen card, pick up (fraud account)  B2  Surcharge amount not supported by debit network issuer.");
        actionCode.put("51", "Not sufficient funds  N0  Force STIP");
        actionCode.put("52", "No checking account  N3  Cash service not available");
        actionCode.put("53", "No savings account  N4  Cash request exceeds issuer or approved limit");
        actionCode.put("54", "Expired card or expiration date is missing  N5  Ineligible for resubmission");
        actionCode.put("55", "Incorrect PIN or PIN missing  N7  Decline for CVV2 failure");
        actionCode.put("57", "Transaction not permitted to cardholder  N8  Transaction amount exceeds preauthorized approval amount ");
        actionCode.put("Q1", "Card Authentication failed");
        actionCode.put("R0", "Stop Payment Order");

    }

    public static Map<String, String> getActionCode() {
        return actionCode;
    }

    public static String getActionCodeMessage(String code) {
        String errorCode = actionCode.get(code);
        return (errorCode != null) ? errorCode : DEFAULT_TYPE;
    }
}
