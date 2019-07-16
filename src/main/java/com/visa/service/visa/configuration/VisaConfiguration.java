package com.visa.service.visa.configuration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Abdussamad
 */
@AllArgsConstructor
@Builder
@Component
@ConfigurationProperties(prefix = "visa")
@Data
@NoArgsConstructor
public class VisaConfiguration {

  private String baseUrl;
  private String username;
  private String password;

}
