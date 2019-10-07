package com.visa.service.service.facade;

import com.visa.service.model.response.SuccessResponse;
import com.visa.service.service.PaymentService;
import com.visa.service.service.VisaService;
import com.visa.service.visa.model.request.CashInRequest;
import com.visa.service.visa.model.request.CashOutRequest;
import com.visa.service.visa.model.request.MerchandiseReturnRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * @author Abdussamad
 */
@Component
public class PaymentFacade {

    private static final String BEARER_PREFIX = "Bearer ";
    private final PaymentService paymentService;
    private final VisaService visaService;

    @Autowired
    public PaymentFacade(PaymentService paymentService, VisaService visaService) {
        Assert.notNull(paymentService);
        Assert.notNull(visaService);
        this.paymentService = paymentService;
        this.visaService = visaService;
    }

    public SuccessResponse requestCashIn(CashInRequest cashInRequest) {
        return visaService.requestCashIn(cashInRequest);
    }

    public SuccessResponse requestCashOut(CashOutRequest cashOutRequest) {
        return visaService.requestCashOut(cashOutRequest);
    }

    public SuccessResponse requestRefund(MerchandiseReturnRequest merchandiseReturnRequest) {
        return visaService.refundTransaction(merchandiseReturnRequest);
    }
}
