package com.visa.service.contoller;

import com.visa.service.exception.VisaApiException;
import com.visa.service.model.entity.User;
import com.visa.service.model.request.AuthenticateUserRequest;
import com.visa.service.model.request.ConfirmEmailRequest;
import com.visa.service.model.request.UpsertUserRequest;
import com.visa.service.model.request.UserSearchRequest;
import com.visa.service.model.response.SuccessResponse;
import com.visa.service.model.response.VisaApiResponse;
import com.visa.service.service.facade.AccountFacade;
import com.visa.service.validator.InputValidator;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Abdussamad
 */
@RestController
@RequestMapping("/v1/users")
public class UserController {

  private AccountFacade accountFacade;

  @Autowired
  public UserController(AccountFacade accountFacade) {
    Assert.notNull(accountFacade);
    this.accountFacade = accountFacade;
  }

  @Transactional
  @RequestMapping(method = RequestMethod.POST, produces = "application/json")
  public ResponseEntity<VisaApiResponse> create(
      @Valid @RequestBody UpsertUserRequest upsertUserRequest,
      BindingResult bindingResult) throws VisaApiException {
    InputValidator.validate(bindingResult);
    SuccessResponse response = accountFacade.createUser(upsertUserRequest);
    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }

  @Transactional
  @RequestMapping(method = RequestMethod.PUT, path = "/{userKey}", produces = "application/json")
  public ResponseEntity<VisaApiResponse> update(
      @PathVariable String userKey,
      @RequestBody UpsertUserRequest upsertUserRequest,
      @RequestHeader("X-User-Token") String userToken) {
    SuccessResponse response = accountFacade.updateUser(upsertUserRequest, userKey, userToken);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @RequestMapping(
      path = "/authenticate",
      method = RequestMethod.POST,
      produces = "application/json")
  public ResponseEntity<VisaApiResponse> authenticate(
      @Valid @RequestBody AuthenticateUserRequest request,
      BindingResult bindingResult) throws VisaApiException {
    InputValidator.validate(bindingResult);
    SuccessResponse response = accountFacade.authenticateUser(request);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @RequestMapping(
      method = RequestMethod.POST,
      path = "/passwordreset",
      produces = "application/json")
  public ResponseEntity<VisaApiResponse> resetPassword(
      @RequestBody UpsertUserRequest upsertUserRequest
  ) {
    SuccessResponse response = accountFacade.resetPassword(upsertUserRequest);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @RequestMapping(
      method = RequestMethod.POST,
      path = "/passwordchange",
      produces = "application/json")
  public ResponseEntity<VisaApiResponse> changePassword(
      @RequestBody UpsertUserRequest upsertUserRequest,
      @RequestHeader("X-User-Token") String userToken
  ) {
    User user = accountFacade.getAuthenticatedUser(userToken);
    SuccessResponse response = accountFacade.changeUserPassword(upsertUserRequest, user);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

//  @RequestMapping(method = RequestMethod.POST, path = "/2fa", produces = "application/json")
//  public ResponseEntity<RizeResponse> initiateTwoFactorAuthentication(
//      @RequestHeader("X-User-Token") String userToken) {
//    User user = accountFacade.getAuthenticatedUser(userToken);
//    SuccessResponse response = accountFacade.initiateTwoFactorAuthentication(user);
//    return new ResponseEntity<>(response, HttpStatus.OK);
//  }

//  @RequestMapping(
//      method = RequestMethod.POST,
//      path = "/2fa/audio",
//      produces = "application/json")
//  public ResponseEntity<RizeResponse> initiateTwoFactorLink(
//      @RequestBody TwoFactorLinkRequest response) {
//    SuccessResponse response = accountFacade.initiateTwoFactorLink(response);
//    return new ResponseEntity<>(response, HttpStatus.OK);
//  }

  @RequestMapping(
      method = RequestMethod.POST,
      path = "/logout",
      produces = "application/json")
  public ResponseEntity<VisaApiResponse> logout(
      @RequestHeader("X-User-Token") String userToken
  ) {
    User user = accountFacade.getAuthenticatedUser(userToken);
    SuccessResponse response = accountFacade.logoutUser(user, userToken);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @RequestMapping(path = "/{userKey}", produces = "application/json")
  public ResponseEntity<VisaApiResponse> getUserInfo(
      @RequestHeader("X-User-Token") String userToken,
      @PathVariable String userKey) {
    User actor = accountFacade.getAuthenticatedUser(userToken);
    SuccessResponse successResponse =
        accountFacade.getUserInfo(userKey, actor);
    return new ResponseEntity<>(successResponse, HttpStatus.OK);
  }

  @Transactional
  @RequestMapping(method = RequestMethod.GET, produces = "application/json")
  public ResponseEntity<VisaApiResponse> getUsers(
      @RequestHeader("X-User-Token") String userToken,
      @ModelAttribute UserSearchRequest userSearchRequest)
      throws VisaApiException {
    User authenticatedUser = accountFacade.getAuthenticatedUser(userToken);
    SuccessResponse response = accountFacade.getAllUsers(authenticatedUser, userSearchRequest);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @RequestMapping(
      method = RequestMethod.POST,
      path = "/confirmemail",
      produces = "application/json")
  public ResponseEntity<VisaApiResponse> confirmEmail(@RequestBody ConfirmEmailRequest request) {
    SuccessResponse response = accountFacade.confirmUserEmail(request);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

}
