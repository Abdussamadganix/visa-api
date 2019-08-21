package com.visa.service.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author Abdussamad
 */
@Configuration
@EnableWebSecurity
@EnableAsync
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .antMatchers(new String[]{"/"}).permitAll();
    http.csrf().disable();
  }

  @Bean
  public WebMvcConfigurer corsConfigurer() {
    return new WebMvcConfigurerAdapter() {
      @Override
      public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/v1/**")
            .allowedMethods("PUT", "DELETE", "POST", "GET")
            .allowedHeaders("X-User-Token", "Authorization", "Content-Type")
            .allowedOrigins("*")
            .allowCredentials(true).maxAge(3600);
      }
    };
  }
}
