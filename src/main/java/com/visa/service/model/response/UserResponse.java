package com.visa.service.model.response;

import com.visa.service.model.entity.User;
import com.visa.service.util.TimeUtil;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Abdussamad
 */
@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class UserResponse {

  private String uniqueKey;
  private String firstName;
  private String lastName;
  private String email;
  private String phone;
  private String status;
  private String createdAt;
  private String updatedAt;

  public static UserResponse fromUser(User user) {
    if (user == null) {
      return null;
    }
    return UserResponse.builder()
        .uniqueKey(user.getUniqueKey())
        .firstName(user.getName())
        .email(user.getEmail())
        .phone(user.getPhone())
        .status(user.getStatus().toString())
        .createdAt(TimeUtil.getIsoTime(user.getCreatedAt()))
        .updatedAt(TimeUtil.getIsoTime(user.getUpdatedAt()))
        .build();
  }

  public static List<UserResponse> fromUsers(List<User> users) {
    return users.stream().map(user -> {
      return fromUser(user);
    }).collect(
        Collectors.toList());
  }

}
