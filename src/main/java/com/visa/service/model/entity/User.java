package com.visa.service.model.entity;

import com.visa.service.model.constant.Status;
import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 * @author Abdussamad
 */
@Entity
public class User {

  private Integer id;
  private String uniqueKey;
  private String name;
  private String email;
  private String phone;
  private String password;
  private Status status;
  private Timestamp createdAt;
  private Timestamp updatedAt;

  @Id
  @Column(name = "id", nullable = false)
  @GeneratedValue
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  @Basic
  @Column(name = "unique_key", nullable = false, length = 32)
  public String getUniqueKey() {
    return uniqueKey;
  }

  public void setUniqueKey(String uniqueKey) {
    this.uniqueKey = uniqueKey;
  }

  @Basic
  @Column(name = "name", nullable = false, length = 525)
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Basic
  @Column(name = "email", nullable = false, length = 255)
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Basic
  @Column(name = "phone", nullable = true, length = 16)
  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  @Basic
  @Column(name = "password", nullable = false, length = 255)
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Basic
  @Column(name = "status", nullable = false, length = 16)
  @Enumerated(value = EnumType.STRING)
  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  @Basic
  @Column(name = "created_at", nullable = false)
  public Timestamp getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Timestamp createdAt) {
    this.createdAt = createdAt;
  }

  @Basic
  @Column(name = "updated_at", nullable = true)
  public Timestamp getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(Timestamp updatedAt) {
    this.updatedAt = updatedAt;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    User user = (User) o;

    if (id != user.id) {
      return false;
    }
    if (uniqueKey != null ? !uniqueKey.equals(user.uniqueKey) : user.uniqueKey != null) {
      return false;
    }
    if (name != null ? !name.equals(user.name) : user.name != null) {
      return false;
    }
    if (email != null ? !email.equals(user.email) : user.email != null) {
      return false;
    }
    if (phone != null ? !phone.equals(user.phone) : user.phone != null) {
      return false;
    }
    if (password != null ? !password.equals(user.password) : user.password != null) {
      return false;
    }
    if (status != null ? !status.equals(user.status) : user.status != null) {
      return false;
    }
    if (createdAt != null ? !createdAt.equals(user.createdAt) : user.createdAt != null) {
      return false;
    }
    return updatedAt != null ? updatedAt.equals(user.updatedAt) : user.updatedAt == null;
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (uniqueKey != null ? uniqueKey.hashCode() : 0);
    result = 31 * result + (name != null ? name.hashCode() : 0);
    result = 31 * result + (email != null ? email.hashCode() : 0);
    result = 31 * result + (phone != null ? phone.hashCode() : 0);
    result = 31 * result + (password != null ? password.hashCode() : 0);
    result = 31 * result + (status != null ? status.hashCode() : 0);
    result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
    result = 31 * result + (updatedAt != null ? updatedAt.hashCode() : 0);
    return result;
  }

  @PrePersist
  public void beforeSave() {
    this.createdAt = new Timestamp(new Date().getTime());
  }

  @PreUpdate
  private void beforeUpdate() {
    this.updatedAt = new Timestamp(new Date().getTime());
  }


}
