package com.visa.service.contoller;

import com.visa.service.model.request.ApiResolveAliasRequest;
import com.visa.service.model.response.SuccessResponse;
import com.visa.service.model.response.VisaApiResponse;
import com.visa.service.service.VisaService;
import com.visa.service.util.LoggingUtil;
import com.visa.service.validator.InputValidator;
import com.visa.service.visa.model.request.ReceiveMerchantPayment;
import com.visa.service.visa.model.request.ReceivePaymentAdvice;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by abdussamad.olaiya on 24/06/2019.
 */
@RestController
@RequestMapping("/callback/notification/payment")
public class CallbackController {

  private VisaService visaService;
  private final LoggingUtil loggingUtil;


  @Autowired
  public CallbackController(VisaService visaService, LoggingUtil loggingUtil) {
    Assert.notNull(visaService);
    Assert.notNull(loggingUtil);
    this.visaService = visaService;
    this.loggingUtil = loggingUtil;
  }

  @RequestMapping(method = RequestMethod.POST,
      produces = "application/json")
  @ResponseStatus(value = HttpStatus.OK)
  public void callbackPaymentNotification(
      @RequestBody ReceiveMerchantPayment request) throws Exception {
    loggingUtil.info(
        "Raw response (Receive Merchant Payment) notification from visa :" + String
            .valueOf(request));
//    SuccessResponse response =
//        visaService.updateTransactionUsingCallbackNotitification(request);
//    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @RequestMapping(path = "/advice", method = RequestMethod.POST,
      produces = "application/json")
  @ResponseStatus(value = HttpStatus.OK)
  public void paymentAdvice(
      @RequestBody ReceivePaymentAdvice request) throws Exception {
    loggingUtil.info(
        "Raw response (Receive Payment advice) notification from visa :" + String.valueOf(request));
//    SuccessResponse response = visaService.resolveAlias(request);
//    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}
