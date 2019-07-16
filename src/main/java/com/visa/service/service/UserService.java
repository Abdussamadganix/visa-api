package com.visa.service.service;

import com.visa.service.exception.AuthenticationException;
import com.visa.service.exception.BadRequestException;
import com.visa.service.exception.ConflictException;
import com.visa.service.exception.NotFoundException;
import com.visa.service.exception.ProcessingException;
import com.visa.service.exception.VisaApiException;
import com.visa.service.model.constant.Status;
import com.visa.service.model.constant.TokenStatus;
import com.visa.service.model.entity.Token;
import com.visa.service.model.entity.User;
import com.visa.service.model.request.UserSearchRequest;
import com.visa.service.repository.TokenRepository;
import com.visa.service.repository.UserRepository;
import com.visa.service.util.LoggingUtil;
import com.visa.service.util.PhoneUtil;
import com.visa.service.util.SecurityUtil;
import com.visa.service.util.TimeUtil;
import com.visa.service.util.VisaBeanUtil;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * @author Abdussamad
 */
@Service
public class UserService {

  private static final String NO_PASSWORD = "password may not be null or empty";
  private static final int SESSION_LIFE = 300;
  private static final String USER_NOT_FOUND = "User not found";
  private final UserRepository userRepository;
  private final TokenRepository tokenRepository;

  @Autowired
  public UserService(UserRepository userRepository, TokenRepository tokenRepository) {
    Assert.notNull(userRepository);
    Assert.notNull(tokenRepository);
    this.userRepository = userRepository;
    this.tokenRepository = tokenRepository;
  }

  @Autowired
  public void setLoggingUtil(LoggingUtil loggingUtil) {
    LoggingUtil loggingUtil1 = loggingUtil;
  }

  public User createUser(User user) throws VisaApiException {
    prepareUserForCreation(user);
    validateUserForCreation(user);
    setUserStatus(user);
    return userRepository.save(user);
  }

  public User authenticateUser(User user) throws AuthenticationException {
    User savedUser = userRepository.findOneByEmail(user.getEmail());
    PasswordEncoder encoder = new BCryptPasswordEncoder();
    if ((savedUser == null)
        || savedUser.getStatus() == Status.INACTIVE
        || !encoder
        .matches(user.getPassword() + savedUser.getUniqueKey(), savedUser.getPassword())) {
      throw new AuthenticationException("User not authenticated");
    }

    return savedUser;
  }

  public Token createToken(User user) throws ProcessingException {
    String rawKey = user.getUniqueKey() + new Date().toString();
    String tokenValue = SecurityUtil.hashWithMd5(rawKey);
    Token token = new Token();
    token.setStatus(TokenStatus.ACTIVE);
    token.setToken(tokenValue);
    token.setUser(user.getUniqueKey());
    token.setExpiresAt(TimeUtil.futureTime(SESSION_LIFE));
    return tokenRepository.save(token);
  }

  public User getAuthenticatedUser(String token) throws AuthenticationException {
    Timestamp now = new Timestamp(new Date().getTime());
    Token userToken = tokenRepository.findOneByToken(token);
    if (userToken == null ||
        userToken.getExpiresAt().before(now) ||
        userToken.getStatus() != TokenStatus.ACTIVE) {
      throw new AuthenticationException("User not authenticated");
    }
    extendSession(userToken);
    return userRepository.findOneByUniqueKey(userToken.getUser());
  }

  public User fetchByUniqueKey(String uniqueKey) {
    User user = userRepository.findOneByUniqueKey(uniqueKey);
    if (user == null) {
      throw new NotFoundException(USER_NOT_FOUND);
    }
    return user;
  }

  public User fetchByEmail(String email) {
    User user = userRepository.findOneByEmail(email);
    if (user == null) {
      throw new NotFoundException(USER_NOT_FOUND);
    }
    return user;
  }

  public User updateUser(User userToUpdate, User updateUser, User authenticatedUser) {
    prepareUserForUpdate(updateUser);
    VisaBeanUtil.copyProperties(updateUser, userToUpdate);
    prepareUserForSave(userToUpdate);
    return userRepository.save(userToUpdate);
  }

  public User addUser(User user) {
    User savedUser = userRepository.findOneByEmail(user.getEmail());
    if (savedUser != null) {
      return savedUser;
    }
    return createUser(user);
  }

  public User resetPassword(User user, User updateUser) {
    if (updateUser.getPassword() == null || updateUser.getPassword().isEmpty()) {
      throw new BadRequestException(NO_PASSWORD);
    }
    user.setPassword(updateUser.getPassword());
    hashPassword(user);
    if (user.getStatus() == Status.NEW) {
      user.setStatus(Status.ACTIVE);
    }
    return userRepository.save(user);
  }

  public void logout(String tokenValue) {
    Token token = tokenRepository.findOneByToken(tokenValue);
    token.setStatus(TokenStatus.INACTIVE);
    tokenRepository.save(token);
  }

  public User confirmEmail(User user) {
    if (user.getStatus() == Status.NEW) {
      user.setStatus(Status.ACTIVE);
    }
    return userRepository.save(user);
  }

  public Page<User> findAllUsers(UserSearchRequest request) {
    return userRepository
        .findAll(request.getBooleanExpression(), request.getPaginationQuery());
  }

  private void prepareUserForSave(User user) throws VisaApiException {
    normalizePhone(user);
  }

  private void prepareUserForCreation(User user) throws VisaApiException {
    generateUniqueKey(user);
    normalizePhone(user);
    hashPassword(user);
  }

  private void generateUniqueKey(User user) throws ProcessingException {
    if (user.getUniqueKey() != null) {
      return;
    }
    String rawKey = user.getEmail() + user.getName() + LocalDateTime.now() + Math.random();
    String uniqueKey = SecurityUtil.hashWithMd5(rawKey);
    user.setUniqueKey(uniqueKey);
  }

  private void normalizePhone(User user) throws BadRequestException {
    if (user.getPhone() == null || user.getPhone().isEmpty()) {
      return;
    }
    String normalizedPhone = PhoneUtil.normalizePhone(user.getPhone());
    user.setPhone(normalizedPhone);
  }

  private void hashPassword(User user) {
    if (user.getPassword() == null) {
      user.setPassword(NO_PASSWORD);
      return;
    }
    PasswordEncoder encoder = new BCryptPasswordEncoder();
    String encodedPassword = encoder.encode(user.getPassword() + user.getUniqueKey());
    user.setPassword(encodedPassword);
  }

  private void validateUserForCreation(User user) throws ConflictException {
    verifyEmailIsUnique(user);
    verifyPhoneIsUnique(user);
  }

  private void verifyEmailIsUnique(User user) throws ConflictException {
    User savedUser = userRepository.findOneByEmail(user.getEmail());
    if (savedUser != null) {
      throw new ConflictException("User with this email already exists");
    }
  }

  private void verifyPhoneIsUnique(User user) throws ConflictException {
    if (user.getPhone() == null || user.getPhone().isEmpty()) {
      return;
    }
    User savedUser = userRepository.findOneByPhone(user.getPhone());
    if (savedUser != null) {
      throw new ConflictException("User with this phone number already exists");
    }
  }

  private void prepareUserForUpdate(User user) {
    user.setPassword(null);
  }

  private void setUserStatus(User user) {
    user.setStatus(Status.ACTIVE);
  }

  private void extendSession(Token token) {
    LocalDateTime tokenExpiryTime = token.getExpiresAt().toLocalDateTime();
    if (ChronoUnit.SECONDS.between(LocalDateTime.now(), tokenExpiryTime) < SESSION_LIFE / 2) {
      token.setExpiresAt(TimeUtil.futureTime(SESSION_LIFE));
      tokenRepository.save(token);
    }
  }
}
