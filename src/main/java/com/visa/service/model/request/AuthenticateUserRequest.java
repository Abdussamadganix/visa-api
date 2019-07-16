package com.visa.service.model.request;

import com.visa.service.model.entity.User;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author Abdussamad
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticateUserRequest {

  @NotNull
  @NotEmpty
  private String email;

  @NotNull
  @NotEmpty
  private String password;

  public User toUser() {
    User user = new User();
    user.setEmail(email);
    user.setPassword(password);
    return user;
  }
}
