package com.visa.service.contoller;

import com.visa.service.exception.VisaApiException;
import com.visa.service.model.entity.User;
import com.visa.service.model.request.ApiCreateMerchantAliasRequest;
import com.visa.service.model.request.ApiUpdateMerchantAliasRequest;
import com.visa.service.model.request.MerchantSearchRequest;
import com.visa.service.model.response.SuccessResponse;
import com.visa.service.model.response.VisaApiResponse;
import com.visa.service.service.VisaService;
import com.visa.service.service.facade.AccountFacade;
import com.visa.service.validator.InputValidator;
import com.visa.service.visa.model.request.DeleteMerchantAliasRequest;
import com.visa.service.visa.model.request.GetMerchantAliasRequest;
import javax.validation.Valid;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/merchantalias")
public class MerchantAliasController {

  private VisaService visaService;
  private AccountFacade accountFacade;

  @Autowired
  public MerchantAliasController(VisaService visaService, AccountFacade accountFacade) {
    this.visaService = visaService;
    this.accountFacade = accountFacade;
  }

  @RequestMapping(path = "/create", method = RequestMethod.POST,
      produces = "application/json")
  public ResponseEntity<VisaApiResponse> createMerchantAlias(
      @Valid @RequestBody ApiCreateMerchantAliasRequest request, BindingResult bindingResult)
      throws Exception {
    InputValidator.validate(bindingResult);
    SuccessResponse response = visaService.createMerchantAlias(request);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @RequestMapping(path = "/update", method = RequestMethod.POST,
      produces = "application/json")
  public ResponseEntity<VisaApiResponse> updateMerchantAlias(
      @Valid @RequestBody ApiUpdateMerchantAliasRequest request, BindingResult bindingResult)
      throws Exception {
    InputValidator.validate(bindingResult);
    SuccessResponse response = visaService.updateMerchantAlias(request);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @RequestMapping(path = "/get", method = RequestMethod.POST,
      produces = "application/json")
  public ResponseEntity<VisaApiResponse> updateMerchantAlias(
      @Valid @RequestBody GetMerchantAliasRequest request, BindingResult bindingResult)
      throws Exception {
    InputValidator.validate(bindingResult);
    SuccessResponse response = visaService.getMerchantAlias(request);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @RequestMapping(path = "/delete", method = RequestMethod.POST,
      produces = "application/json")
  public ResponseEntity<VisaApiResponse> deleteMerchantAlias(
      @Valid @RequestBody DeleteMerchantAliasRequest request, BindingResult bindingResult)
      throws Exception {
    InputValidator.validate(bindingResult);
    SuccessResponse response = visaService.deleteMerchantAlias(request);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @Transactional
  @RequestMapping(method = RequestMethod.GET, produces = "application/json")
  public ResponseEntity<VisaApiResponse> getMerchantAliases(
      @RequestHeader("X-User-Token") String userToken,
      @ModelAttribute MerchantSearchRequest merchantSearchRequest)
      throws VisaApiException {
    User authenticatedUser = accountFacade.getAuthenticatedUser(userToken);
    SuccessResponse response = accountFacade
        .getAllMerchantAliases(authenticatedUser, merchantSearchRequest);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}
