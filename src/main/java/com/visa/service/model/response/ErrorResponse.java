package com.visa.service.model.response;

import com.visa.service.model.constant.ErrorCode;
import com.visa.service.model.constant.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Abdussamad
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ErrorResponse extends VisaApiResponse {

  private Status status;
  private ErrorCode error;
  private String message;
}
