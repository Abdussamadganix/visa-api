package com.visa.service.contoller;


import com.visa.service.model.request.ApiCreateAliasRequest;
import com.visa.service.model.request.ApiDeleteAliasRequest;
import com.visa.service.model.request.ApiGetAliasRequest;
import com.visa.service.model.request.ApiResolveAliasRequest;
import com.visa.service.model.request.ApiUpdateAliasRequest;
import com.visa.service.model.response.SuccessResponse;
import com.visa.service.model.response.VisaApiResponse;
import com.visa.service.service.VisaService;
import com.visa.service.service.facade.AccountFacade;
import com.visa.service.validator.InputValidator;
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

/**
 * @author Abdussamad
 */
@RestController
@RequestMapping("/v1/alias")
public class AliasController {

  private VisaService visaService;
  private AccountFacade accountFacade;

  @Autowired
  public AliasController(VisaService visaService, AccountFacade accountFacade) {
    this.visaService = visaService;
    this.accountFacade = accountFacade;
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


}
