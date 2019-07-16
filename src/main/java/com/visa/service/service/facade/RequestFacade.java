package com.visa.service.service.facade;

import com.visa.service.model.response.SuccessResponse;
import java.util.HashMap;
import java.util.Map;
import org.springframework.data.domain.Page;

/**
 * @author Abdussamad
 */
public abstract class RequestFacade {

  SuccessResponse buildSuccessResponse(Map<String, Object> data) {
    return SuccessResponse.builder()
        .data(data)
        .build();
  }

  SuccessResponse buildPaginatedSuccessResponse(
      String contentName, Page<?> page) {
    Map<String, Object> data = new HashMap<>();
    Map<String, Object> pageData = new HashMap<>();
    pageData.put("content", page.getContent());
    pageData.put("totalPages", page.getTotalPages());
    pageData.put("pageNumber", page.getNumber());
    pageData.put("size", page.getSize());
    pageData.put("totalElements", page.getTotalElements());
    pageData.put("numberOfElements", page.getNumberOfElements());
    pageData.put("first", page.isFirst());
    pageData.put("last", page.isLast());
    data.put(contentName, pageData);
    return buildSuccessResponse(data);
  }
}
