package dev.fun.store.backend.configuration.security;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class RestHttpStatusReturningLogoutSuccessHandler extends HttpStatusReturningLogoutSuccessHandler {
  
  @Override
  public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
      throws IOException {
    
  	log.info(
  			"URI: " + request.getRequestURI()
  			+ " PRINCIPAL: " 
  					+ ((request.getUserPrincipal() == null) ? "NULL" : request.getUserPrincipal().getName()));
  	
    super.onLogoutSuccess(request, response, authentication);
    
  }
  
}
