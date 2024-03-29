/* ----------------------------------------------------------------------------------------------------------------------
* © Copyright 2018 Visa. All Rights Reserved.
*
* NOTICE: The software and accompanying information and documentation (together, the “Software”) remain the property of
* and are proprietary to Visa and its suppliers and affiliates. The Software remains protected by intellectual property
* rights and may be covered by U.S. and foreign patents or patent applications. The Software is licensed and not sold.
*
* By accessing the Software you are agreeing to Visa's terms of use (developer.visa.com/terms) and privacy policy
* (developer.visa.com/privacy). In addition, all permissible uses of the Software must be in support of Visa products,
* programs and services provided through the Visa Developer Program (VDP) platform only (developer.visa.com).
* THE SOFTWARE AND ANY ASSOCIATED INFORMATION OR DOCUMENTATION IS PROVIDED ON AN “AS IS,” “AS AVAILABLE,” “WITH ALL
* FAULTS” BASIS WITHOUT WARRANTY OR CONDITION OF ANY KIND. YOUR USE IS AT YOUR OWN RISK.
---------------------------------------------------------------------------------------------------------------------- */

/*
 * mVisa API
 * mVisa is a simple, secure and fast way to pay and be paid using mobile phones. mVisa enables a range of payment use cases and is technology agnostic-leveraging evolving POS environment such as QR codes and works on both smart or feature phones.
 *
 * OpenAPI spec version: v1
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package com.visa.service.visa.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

/**
 * Encrypted Payload
 */
public class EncryptedResponse {


  @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
  @JsonProperty("encData")
  private String encData = null;

  public EncryptedResponse encData(String encData) {
    this.encData = encData;
    return this;
  }


  /**
   * Encrypted payload string
   *
   * @return encData
   **/
  public String getEncData() {
    return encData;
  }

  public void setEncData(String encData) {
    this.encData = encData;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EncryptedResponse encryptedResponse = (EncryptedResponse) o;
    return Objects.equals(this.encData, encryptedResponse.encData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(encData);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EncryptedResponse {\n");

    sb.append("    encData: ").append(toIndentedString(encData)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }


}



