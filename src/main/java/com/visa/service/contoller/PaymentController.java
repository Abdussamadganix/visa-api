package com.visa.service.contoller;

import com.visa.service.exception.VisaApiException;
import com.visa.service.model.entity.User;
import com.visa.service.model.request.MerchantSearchRequest;
import com.visa.service.model.request.PaymentRequest;
import com.visa.service.model.request.PaymentSearchRequest;
import com.visa.service.model.request.UpsertUserRequest;
import com.visa.service.model.response.SuccessResponse;
import com.visa.service.model.response.VisaApiResponse;
import com.visa.service.service.PaymentService;
import com.visa.service.service.facade.AccountFacade;
import com.visa.service.service.facade.PaymentFacade;
import com.visa.service.validator.InputValidator;
import javax.validation.Valid;

import com.visa.service.visa.model.request.CashInRequest;
import com.visa.service.visa.model.request.CashOutRequest;
import com.visa.service.visa.model.request.MerchandiseReturnRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Abdussamad
 */
@RestController
@RequestMapping("/v1/payments")
public class PaymentController {

  private PaymentService paymentService;
  private AccountFacade accountFacade;
  private PaymentFacade paymentFacade;

  @Autowired
  public PaymentController(PaymentService paymentService, AccountFacade accountFacade, PaymentFacade paymentFacade) {
    this.paymentService = paymentService;
    this.accountFacade = accountFacade;
    this.paymentFacade = paymentFacade;
  }

  @RequestMapping(method = RequestMethod.POST, produces = "application/json")
  public ResponseEntity<VisaApiResponse> create(
      @Valid @RequestBody PaymentRequest paymentRequest,
      BindingResult bindingResult) throws VisaApiException {
    InputValidator.validate(bindingResult);
    SuccessResponse response = paymentService.logPayment(paymentRequest);
    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }

  @RequestMapping(params = "uniqueKey", produces = "application/json")
  public ResponseEntity<VisaApiResponse> getPayment(
      @RequestParam String uniqueKey) throws VisaApiException {
    SuccessResponse successResponse = paymentService.fetchByUniqueKey(uniqueKey);
    return new ResponseEntity<>(successResponse, HttpStatus.OK);
  }

  @Transactional
  @RequestMapping(method = RequestMethod.GET, produces = "application/json")
  public ResponseEntity<VisaApiResponse> getMerchantAliases(
      @RequestHeader("X-User-Token") String userToken,
      @ModelAttribute PaymentSearchRequest paymentSearchRequest)
      throws VisaApiException {
    User authenticatedUser = accountFacade.getAuthenticatedUser(userToken);
    SuccessResponse response = accountFacade.getPayments(authenticatedUser, paymentSearchRequest);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @RequestMapping(path = "/cashin", method = RequestMethod.POST, produces = "application/json")
  public ResponseEntity<VisaApiResponse> cashIn(
          @Valid @RequestBody CashInRequest cashInRequest,
          BindingResult bindingResult) throws VisaApiException {
    InputValidator.validate(bindingResult);
    SuccessResponse response = paymentFacade.requestCashIn(cashInRequest);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @RequestMapping(path = "/cashout", method = RequestMethod.POST, produces = "application/json")
  public ResponseEntity<VisaApiResponse> cashIn(
          @Valid @RequestBody CashOutRequest cashOutRequest,
          BindingResult bindingResult) throws VisaApiException {
    InputValidator.validate(bindingResult);
    SuccessResponse response = paymentFacade.requestCashOut(cashOutRequest);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @RequestMapping(path = "/refund", method = RequestMethod.POST, produces = "application/json")
  public ResponseEntity<VisaApiResponse> cashIn(
          @Valid @RequestBody MerchandiseReturnRequest merchandiseReturnRequest,
          BindingResult bindingResult) throws VisaApiException {
    InputValidator.validate(bindingResult);
    SuccessResponse response = paymentFacade.requestRefund(merchandiseReturnRequest);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }


}
