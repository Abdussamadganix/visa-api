package com.visa.service.model.response;

import com.visa.service.model.constant.Status;
import java.util.Map;
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
public class SuccessResponse extends VisaApiResponse {

  private Status status = Status.SUCCESS;
  private Map<String, Object> data;
}
