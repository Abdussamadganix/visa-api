package com.visa.service.model.response;

import com.visa.service.model.constant.Status;
import com.visa.service.model.entity.MerchantAlias;
import com.visa.service.model.entity.User;
import com.visa.service.util.TimeUtil;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;
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
public class MerchantAliasResponse {

  private Integer id;
  private String aliasId;
  private String isAgentAlias;
  private String merchantId;
  private String merchantCategoryCode;
  private String payloadFormatIndicator;
  private String pointOfInitiationMethod;
  private String transactionCurrencyCode;
  private String tipConvenienceFeeIndicator;
  private String convenienceFeeAmount;
  private String convenienceFeePercentage;
  private String recipientName;
  private String city;
  private String country;

  private String terminalId;
  private Status createStatus;
  private Status updateStatus;
  private Status deleteStatus;
  private String errorReason;
  private String postalCode;
  private String email;
  private String phone;
  private String createdAt;
  private String updatedAt;
  private Status status;

  public static MerchantAliasResponse fromMerchantAlias(MerchantAlias merchantAlias) {
    return MerchantAliasResponse.builder()
        .aliasId(merchantAlias.getAliasId())
        .isAgentAlias(merchantAlias.getIsAgentAlias())
        .merchantId(merchantAlias.getMerchantId())
        .merchantCategoryCode(merchantAlias.getMerchantCategoryCode())
        .payloadFormatIndicator(merchantAlias.getPayloadFormatIndicator())
        .pointOfInitiationMethod(merchantAlias.getPointOfInitiationMethod())
        .transactionCurrencyCode(merchantAlias.getTransactionCurrencyCode())
        .tipConvenienceFeeIndicator(merchantAlias.getTipConvenienceFeeIndicator())
        .convenienceFeeAmount(merchantAlias.getConvenienceFeeAmount())
        .convenienceFeePercentage(merchantAlias.getConvenienceFeePercentage())
        .recipientName(merchantAlias.getRecipientName())
        .city(merchantAlias.getCity())
        .country(merchantAlias.getCountry())
        .terminalId(merchantAlias.getTerminalId())
        .createStatus(merchantAlias.getCreateStatus())
        .updateStatus(merchantAlias.getUpdateStatus())
        .deleteStatus(merchantAlias.getDeleteStatus())
        .errorReason(merchantAlias.getErrorReason())
        .postalCode(merchantAlias.getPostalCode())
        .email(merchantAlias.getEmail())
        .phone(merchantAlias.getPhone())
        .createdAt(TimeUtil.getIsoTime(merchantAlias.getCreatedAt()))
        .updatedAt(TimeUtil.getIsoTime(merchantAlias.getUpdatedAt()))
        .status(merchantAlias.getStatus())
        .build();
  }

  public static List<MerchantAliasResponse> fromMerchantAliases(
      List<MerchantAlias> merchantAliases) {
    return merchantAliases.stream().map(merchantAlias -> {
      return fromMerchantAlias(merchantAlias);
    }).collect(
        Collectors.toList());
  }


}
