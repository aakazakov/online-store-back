package dev.fun.store.backend.configuration.security;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class RestHttpStatusReturningLogoutSuccessHandler extends HttpStatusReturningLogoutSuccessHandler {
  
  @Override
  public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
      throws IOException {
    
    super.onLogoutSuccess(request, response, authentication);
    
  }
  
}
