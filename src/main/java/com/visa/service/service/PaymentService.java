package com.visa.service.service;

import com.visa.service.exception.NotFoundException;
import com.visa.service.model.entity.MerchantAlias;
import com.visa.service.model.entity.Payment;
import com.visa.service.model.request.MerchantSearchRequest;
import com.visa.service.model.request.PaymentRequest;
import com.visa.service.model.request.PaymentSearchRequest;
import com.visa.service.model.response.PaymentResponse;
import com.visa.service.model.response.SuccessResponse;
import com.visa.service.repository.PaymentRepository;
import com.visa.service.util.SecurityUtil;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

/**
 * @author Abdussamad
 */
@Service
public class PaymentService {

  private final PaymentRepository paymentRepository;
  private static final String PAYMENT_NOT_FOUND = "Payment not found";


  @Autowired
  public PaymentService(PaymentRepository paymentRepository) {
    this.paymentRepository = paymentRepository;
  }

  public SuccessResponse logPayment(PaymentRequest paymentRequest) {
    Payment payment = paymentRequest.toPayment();
    generateUniqueKey(payment);
    return SuccessResponse.builder()
        .data(createPaymentResponseData(paymentRepository.save(payment)))
        .build();
  }

  public SuccessResponse fetchByUniqueKey(String uniqueKey) {
    Payment payment = paymentRepository.findOneByUniqueKey(uniqueKey);
    if (payment == null) {
      throw new NotFoundException(PAYMENT_NOT_FOUND);
    }
    return SuccessResponse.builder()
        .data(createPaymentResponseData(payment))
        .build();
  }

  private Map<String, Object> createPaymentResponseData(Payment payment) {
//    PaymentResponse paymentResponse = PaymentResponse.fromPayment(payment);
    Map<String, Object> data = new HashMap<>(1);
    data.put("payment", payment);
    return data;
  }

  private void generateUniqueKey(Payment payment) {
    String rawKey = payment.getBillId() + payment.getTerminalId() + new Date();
    String encodedKey = SecurityUtil.hashWithMd5(rawKey);
    payment.setUniqueKey(encodedKey.substring(0, 10));
  }

  public Page<Payment> findAllPayment(PaymentSearchRequest request) {
    return paymentRepository
        .findAll(request.getBooleanExpression(), request.getPaginationQuery());
  }

}
