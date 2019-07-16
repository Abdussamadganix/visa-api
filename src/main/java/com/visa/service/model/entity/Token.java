package com.visa.service.model.entity;

import com.visa.service.model.constant.TokenStatus;
import java.io.Serializable;
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
public class Token implements Serializable {

  private int id;
  private Timestamp createdAt;
  private Timestamp updatedAt;
  private Timestamp expiresAt;
  private TokenStatus status;
  private String token;
  private String user;

  @Id
  @Column(name = "id", nullable = false)
  @GeneratedValue
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
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

  @Basic
  @Column(name = "expires_at", nullable = true)
  public Timestamp getExpiresAt() {
    return expiresAt;
  }

  public void setExpiresAt(Timestamp expiresAt) {
    this.expiresAt = expiresAt;
  }

  @Basic
  @Column(name = "status", nullable = true, length = 16)
  @Enumerated(EnumType.STRING)
  public TokenStatus getStatus() {
    return status;
  }

  public void setStatus(TokenStatus status) {
    this.status = status;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Token token = (Token) o;

    if (id != token.id) {
      return false;
    }
    if (createdAt != null ? !createdAt.equals(token.createdAt) : token.createdAt != null) {
      return false;
    }
    if (updatedAt != null ? !updatedAt.equals(token.updatedAt) : token.updatedAt != null) {
      return false;
    }
    if (expiresAt != null ? !expiresAt.equals(token.expiresAt) : token.expiresAt != null) {
      return false;
    }
    return status != null ? status.equals(token.status) : token.status == null;
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
    result = 31 * result + (updatedAt != null ? updatedAt.hashCode() : 0);
    result = 31 * result + (expiresAt != null ? expiresAt.hashCode() : 0);
    result = 31 * result + (status != null ? status.hashCode() : 0);
    return result;
  }

  @Basic
  @Column(name = "token", nullable = false, length = 64)
  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  @PrePersist
  public void beforeSave() {
    this.createdAt = new Timestamp(new Date().getTime());
  }

  @PreUpdate
  private void beforeUpdate() {
    this.updatedAt = new Timestamp(new Date().getTime());
  }

  @Basic
  @Column(name = "user", nullable = false, length = 32)
  public String getUser() {
    return user;
  }

  public void setUser(String user) {
    this.user = user;
  }
}
