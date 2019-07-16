package com.visa.service.model.entity;

import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

/**
 * @author Abdussamad
 */
@Entity
@Table(name = "log")
public class Log {

  private Integer id;
  private String system;
  private String requestBody;
  private String responseBody;
  private String responseCode;
  private String status;
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
  @Column(name = "system", nullable = true, length = 32)
  public String getSystem() {
    return system;
  }

  public void setSystem(String system) {
    this.system = system;
  }

  @Basic
  @Column(name = "request_body", nullable = true, length = 10240)
  public String getRequestBody() {
    return requestBody;
  }

  public void setRequestBody(String requestBody) {
    this.requestBody = requestBody;
  }

  @Basic
  @Column(name = "response_body", nullable = true, length = 10240)
  public String getResponseBody() {
    return responseBody;
  }

  public void setResponseBody(String responseBody) {
    this.responseBody = responseBody;
  }

  @Basic
  @Column(name = "response_code", nullable = true, length = 4)
  public String getResponseCode() {
    return responseCode;
  }

  public void setResponseCode(String responseCode) {
    this.responseCode = responseCode;
  }

  @Basic
  @Column(name = "status", nullable = true, length = 16)
  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
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

    Log log = (Log) o;

    if (id != null ? !id.equals(log.id) : log.id != null) {
      return false;
    }
    if (system != null ? !system.equals(log.system) : log.system != null) {
      return false;
    }
    if (requestBody != null ? !requestBody.equals(log.requestBody) : log.requestBody != null) {
      return false;
    }
    if (responseBody != null ? !responseBody.equals(log.responseBody) : log.responseBody != null) {
      return false;
    }
    if (responseCode != null ? !responseCode.equals(log.responseCode) : log.responseCode != null) {
      return false;
    }
    if (status != null ? !status.equals(log.status) : log.status != null) {
      return false;
    }
    if (createdAt != null ? !createdAt.equals(log.createdAt) : log.createdAt != null) {
      return false;
    }
    if (updatedAt != null ? !updatedAt.equals(log.updatedAt) : log.updatedAt != null) {
      return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (system != null ? system.hashCode() : 0);
    result = 31 * result + (requestBody != null ? requestBody.hashCode() : 0);
    result = 31 * result + (responseBody != null ? responseBody.hashCode() : 0);
    result = 31 * result + (responseCode != null ? responseCode.hashCode() : 0);
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
