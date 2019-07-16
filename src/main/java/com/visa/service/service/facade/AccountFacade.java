package com.visa.service.service.facade;

import com.visa.service.model.entity.Token;
import com.visa.service.model.entity.User;
import com.visa.service.model.request.AuthenticateUserRequest;
import com.visa.service.model.request.ConfirmEmailRequest;
import com.visa.service.model.request.UpsertUserRequest;
import com.visa.service.model.request.UserSearchRequest;
import com.visa.service.model.response.AuthResponse;
import com.visa.service.model.response.SuccessResponse;
import com.visa.service.model.response.UserResponse;
import com.visa.service.service.UserService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * @author Abdussamad
 */
@Component
public class AccountFacade extends RequestFacade {

  private static final String BEARER_PREFIX = "Bearer ";
  private final UserService userService;

  @Autowired
  public AccountFacade(UserService userService) {
    Assert.notNull(userService);
    this.userService = userService;
  }

  public SuccessResponse createUser(UpsertUserRequest upsertUserRequest) {
    User user = userService.createUser(upsertUserRequest.toUser());
    return buildSuccessResponse(createUserResponseData(user));
  }

  public SuccessResponse updateUser(
      UpsertUserRequest upsertUserRequest, String userKey, String userToken) {
    User userToUpdate = userService.fetchByUniqueKey(userKey);
    User updateUser = upsertUserRequest.toUser();
    User actor = userService.getAuthenticatedUser(userToken);
    User savedUser = userService.updateUser(userToUpdate, updateUser, actor);
    return buildSuccessResponse(createUserResponseData(savedUser));
  }

  public SuccessResponse authenticateUser(AuthenticateUserRequest authenticateUserRequest) {
    User userToAuthenticate = authenticateUserRequest.toUser();
    User authenticatedUser = userService.authenticateUser(userToAuthenticate);
//    notificationService.sendLoginSuccessful(authenticatedUser);
    Token token = userService.createToken(authenticatedUser);
    Map<String, Object> data = createUserResponseData(authenticatedUser);
    data.put("auth", AuthResponse.fromToken(token));
    return buildSuccessResponse(data);
  }

  public SuccessResponse resetPassword(UpsertUserRequest upsertUserRequest) {
    User user = userService.fetchByEmail(upsertUserRequest.getEmail());
//    twoFactorService.verifyToken(upsertUserRequest.getTwoFactorAuthentication(), user);
    user = changePassword(upsertUserRequest, user);
    return buildSuccessResponse(createUserResponseData(user));
  }

  public SuccessResponse changeUserPassword(UpsertUserRequest upsertUserRequest, User user) {
    user = changePassword(upsertUserRequest, user);
    return buildSuccessResponse(createUserResponseData(user));
  }

  public User getAuthenticatedUser(String userToken) {
    return userService.getAuthenticatedUser(userToken);
  }

  public SuccessResponse logoutUser(User user, String token) {
    userService.logout(token);
    return buildSuccessResponse(createUserResponseData(user));
  }

  public SuccessResponse getUserInfo(String userKey, User actor) {
    User user = userService.fetchByUniqueKey(userKey);
    Map<String, Object> data = createUserResponseData(user);
    return buildSuccessResponse(data);
  }

  public SuccessResponse getAllUsers(User authenticatedUser, UserSearchRequest request) {
    SuccessResponse successResponse = createUserPaginatedResponse(
        userService.findAllUsers(request));
    return successResponse;
  }

  public SuccessResponse confirmUserEmail(ConfirmEmailRequest request) {
    User user = userService.fetchByEmail(request.getEmail());
    user = userService.confirmEmail(user);
    return buildSuccessResponse(createUserResponseData(user));
  }

  private Map<String, Object> createUserResponseData(User user) {
    UserResponse userResponse = UserResponse.fromUser(user);
    Map<String, Object> data = new HashMap<>(1);
    data.put("user", userResponse);
    return data;
  }

  private User changePassword(UpsertUserRequest upsertUserRequest, User user) {
    User updateUser = upsertUserRequest.toUser();
    updateUser.setPassword(upsertUserRequest.getPassword());
    user = userService.resetPassword(user, updateUser);
//    notificationService.sendPasswordChanged(user);
    return user;
  }

  private SuccessResponse createUserPaginatedResponse(Page<User> paymentsPage) {
    String contentName = "users";
    Page<UserResponse> userResponses = paymentsPage.map(UserResponse::fromUser);
    return buildPaginatedSuccessResponse(contentName, userResponses);
  }

}
