package dev.fun.store.backend.configuration.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class RestAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

  @Override
  public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
      AuthenticationException exception) throws IOException, ServletException {
    
  	log.info(
  			"URI: " + request.getRequestURI()
  			+ " PRINCIPAL: " 
  					+ ((request.getUserPrincipal() == null) ? "NULL" : request.getUserPrincipal().getName()));
  	
    super.onAuthenticationFailure(request, response, exception);
  }

}
