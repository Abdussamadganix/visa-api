package com.visa.service.model.request;

import com.visa.service.model.constant.Status;
import com.visa.service.model.entity.Payment;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author Abdussamad
 */
@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class PaymentRequest {

  @NotNull
  @NotEmpty
  private String merchantId;
  @NotNull
  @NotEmpty
  private String amount;
  @NotNull
  @NotEmpty
  private String currencyCode;
  @NotNull
  @NotEmpty
  private String terminalId;
  @NotNull
  @NotEmpty
  private String billId;

  public Payment toPayment() {
    Payment payment = new Payment();
    payment.setMerchantId(merchantId);
    payment.setAmount(amount);
    payment.setBillId(billId);
    payment.setTerminalId(terminalId);
    payment.setCurrency(currencyCode);
    payment.setStatus(Status.PENDING);
    return payment;
  }

}
