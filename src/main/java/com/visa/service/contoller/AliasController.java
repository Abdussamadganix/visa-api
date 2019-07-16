package com.visa.service.contoller;

import com.visa.service.model.request.ApiCreateAliasRequest;
import com.visa.service.model.request.ApiDeleteAliasRequest;
import com.visa.service.model.request.ApiGetAliasRequest;
import com.visa.service.model.request.ApiResolveAliasRequest;
import com.visa.service.model.request.ApiUpdateAliasRequest;
import com.visa.service.model.response.SuccessResponse;
import com.visa.service.model.response.VisaApiResponse;
import com.visa.service.service.VisaService;
import com.visa.service.validator.InputValidator;
import com.visa.service.visa.model.request.CreateMerchantAliasRequest;
import com.visa.service.visa.model.request.DeleteMerchantAliasRequest;
import com.visa.service.visa.model.request.GetMerchantAliasRequest;
import com.visa.service.visa.model.request.UpdateMerchantAliasRequest;
import javax.validation.Valid;
import javax.xml.ws.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Abdussamad
 */
@RestController
@RequestMapping("/alias")
public class AliasController {

  private VisaService visaService;

  @Autowired
  public AliasController(VisaService visaService) {
    this.visaService = visaService;
  }

  @RequestMapping(path = "/resolve", method = RequestMethod.POST,
      produces = "application/json")
  public ResponseEntity<VisaApiResponse> resolveAlias(
      @Valid @RequestBody ApiResolveAliasRequest request, BindingResult bindingResult)
      throws Exception {
    InputValidator.validate(bindingResult);
    SuccessResponse response = visaService.resolveAlias(request);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @RequestMapping(path = "/create", method = RequestMethod.POST,
      produces = "application/json")
  public ResponseEntity<VisaApiResponse> createAlias(
      @Valid @RequestBody ApiCreateAliasRequest request, BindingResult bindingResult)
      throws Exception {
    InputValidator.validate(bindingResult);
    SuccessResponse response = visaService.createAlias(request);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @RequestMapping(path = "/update", method = RequestMethod.POST,
      produces = "application/json")
  public ResponseEntity<VisaApiResponse> updateAlias(
      @Valid @RequestBody ApiUpdateAliasRequest request, BindingResult bindingResult)
      throws Exception {
    InputValidator.validate(bindingResult);
    SuccessResponse response = visaService.updateAlias(request);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @RequestMapping(path = "/get", method = RequestMethod.POST,
      produces = "application/json")
  public ResponseEntity<VisaApiResponse> getAlias(
      @Valid @RequestBody ApiGetAliasRequest request, BindingResult bindingResult)
      throws Exception {
    InputValidator.validate(bindingResult);
    SuccessResponse response = visaService.getAlias(request);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @RequestMapping(path = "/delete", method = RequestMethod.POST,
      produces = "application/json")
  public ResponseEntity<VisaApiResponse> deleteAlias(
      @Valid @RequestBody ApiDeleteAliasRequest request, BindingResult bindingResult)
      throws Exception {
    InputValidator.validate(bindingResult);
    SuccessResponse response = visaService.deleteAlias(request);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @RequestMapping(path = "/merchants/create", method = RequestMethod.POST,
      produces = "application/json")
  public ResponseEntity<VisaApiResponse> createMerchantAlias(
      @Valid @RequestBody CreateMerchantAliasRequest request, BindingResult bindingResult)
      throws Exception {
    InputValidator.validate(bindingResult);
    SuccessResponse response = visaService.createMerchantAlias(request);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @RequestMapping(path = "/merchants/update", method = RequestMethod.POST,
      produces = "application/json")
  public ResponseEntity<VisaApiResponse> updateMerchantAlias(
      @Valid @RequestBody UpdateMerchantAliasRequest request, BindingResult bindingResult)
      throws Exception {
    InputValidator.validate(bindingResult);
    SuccessResponse response = visaService.updateMerchantAlias(request);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @RequestMapping(path = "/merchants/get", method = RequestMethod.POST,
      produces = "application/json")
  public ResponseEntity<VisaApiResponse> updateMerchantAlias(
      @Valid @RequestBody GetMerchantAliasRequest request, BindingResult bindingResult)
      throws Exception {
    InputValidator.validate(bindingResult);
    SuccessResponse response = visaService.getMerchantAlias(request);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @RequestMapping(path = "/merchants/delete", method = RequestMethod.POST,
      produces = "application/json")
  public ResponseEntity<VisaApiResponse> deleteMerchantAlias(
      @Valid @RequestBody DeleteMerchantAliasRequest request, BindingResult bindingResult)
      throws Exception {
    InputValidator.validate(bindingResult);
    SuccessResponse response = visaService.deleteMerchantAlias(request);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

}
