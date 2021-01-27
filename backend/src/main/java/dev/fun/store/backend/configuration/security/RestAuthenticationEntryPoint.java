package dev.fun.store.backend.configuration.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
      throws IOException, ServletException {
        
  	log.info(
  			"URI: " + request.getRequestURI()
  			+ " PRINCIPAL: " 
  					+ ((request.getUserPrincipal() == null) ? "NULL" : request.getUserPrincipal().getName()));
  	
    response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
  }

}
