package dev.fun.store.backend.configuration.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class RestAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) throws IOException, ServletException {
  	
  	log.info(
  			"URI: " + request.getRequestURI()
  			+ " PRINCIPAL: " 
  					+ ((request.getUserPrincipal() == null) ? "NULL" : request.getUserPrincipal().getName()));
  	
    super.clearAuthenticationAttributes(request);
  }

}
