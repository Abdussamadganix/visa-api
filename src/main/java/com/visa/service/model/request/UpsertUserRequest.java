package com.visa.service.model.request;

import com.visa.service.model.constant.Status;
import com.visa.service.model.entity.User;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author Abdussamad
 */
@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class UpsertUserRequest {

  @NotNull
  @NotEmpty
  private String name;

  @NotNull
  @NotEmpty
  @Email
  private String email;

  @NotNull
  @NotEmpty
  private String password;

  private String phone;

  private Status status;

  public User toUser() {
    User user = new User();
    user.setName(name);
    user.setEmail(email);
    user.setPassword(password);
    user.setPhone(phone);
    user.setStatus(status);
    return user;
  }

}
