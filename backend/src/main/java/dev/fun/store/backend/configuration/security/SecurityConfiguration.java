package dev.fun.store.backend.configuration.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.expression.SecurityExpressionHandler;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import dev.fun.store.backend.domain.authority.Role;
import dev.fun.store.backend.service.UserService;

public class SecurityConfiguration extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {
	
  @Autowired
  private RestAuthenticationEntryPoint restAuthenticationEntryPoint;
  
  @Autowired
  private RestAuthenticationSuccessHandler restAuthenticationSuccessHandler;
  
  @Autowired
  private RestAuthenticationFailureHandler restAuthenticationFailureHandler;
  
  @Autowired
  private RestHttpStatusReturningLogoutSuccessHandler restHttpStatusReturningLogoutSuccessHandler;
  
  @Autowired
  private UserService userService;
  
  @Override
  protected void configure(HttpSecurity http) throws Exception {
  	http
  		.authorizeRequests()
  		.expressionHandler(webExpressionHandler())
  		.mvcMatchers(HttpMethod.POST,
  					"/categories/add",
  					"/products/add"
  				).hasRole(Role.ADMIN.name())
  		.mvcMatchers(HttpMethod.PUT,
  					"/authorities/**",
  					"/categories/update",
  					"/products/update"
  				).hasRole(Role.ADMIN.name())
  		.mvcMatchers(HttpMethod.DELETE,
  					"/categories/delete/{\\d+}",
  					"/products/delete/{\\d+}",
  					"/users/delete/{\\d+}"
  				).hasRole(Role.ADMIN.name())
  		.mvcMatchers(HttpMethod.GET,
  					"/authorities/**"
  				).hasRole(Role.ADMIN.name())
			.mvcMatchers(HttpMethod.PUT,
						"/orders/update"
					).hasRole(Role.MANAGER.name())
			.mvcMatchers(HttpMethod.DELETE,
						"/orders/delete/{\\d+}"
					).hasRole(Role.MANAGER.name())
			.mvcMatchers(HttpMethod.GET,
						"/users/all"
					).hasRole(Role.MANAGER.name())
  		.mvcMatchers(HttpMethod.POST,
						"/baskets/create",
						"/orders/create"
				).hasRole(Role.CLIENT.name())
			.mvcMatchers(HttpMethod.PUT,
						"/baskets/add-products",
						"/baskets/remove-products"
					).hasRole(Role.CLIENT.name())
			.mvcMatchers(HttpMethod.DELETE,
						"/baskets/delete/{\\d+}"
					).hasRole(Role.CLIENT.name())
			.mvcMatchers(HttpMethod.GET,
						"/baskets/id/{\\d+}",
						"/baskets/user/{\\d+}",
						"/orders/all/user/{\\d+}",
						"/orders/id/{\\d+}"
					).hasRole(Role.CLIENT.name())
			.mvcMatchers(HttpMethod.GET,
					"/categories/all",
					"/categories/id/{\\d+}",
					"/products/all",
					"/products/id/{\\d+}"
				).hasRole(Role.ANONYMOUS.name())
			.mvcMatchers("/error").hasRole(Role.ANONYMOUS.name())
			.anyRequest().denyAll();
    
    http.exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint);
    http.formLogin().successHandler(restAuthenticationSuccessHandler);
    http.formLogin().failureHandler(restAuthenticationFailureHandler);
    http.logout().logoutSuccessHandler(restHttpStatusReturningLogoutSuccessHandler);
  }
  
  
  @Override
  public void addCorsMappings(CorsRegistry registry) {
      registry.addMapping("/**")
              .allowedOrigins("*") // Temporarily. Not secure.
              .allowedMethods("*");
  }
  
  @Bean
  public DaoAuthenticationProvider provider() {
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setPasswordEncoder(passwordEncoder());
    provider.setUserDetailsService(userService);
    return provider;
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
  
  private SecurityExpressionHandler<FilterInvocation> webExpressionHandler() {
    DefaultWebSecurityExpressionHandler defaultWebSecurityExpressionHandler = new DefaultWebSecurityExpressionHandler();
    defaultWebSecurityExpressionHandler.setRoleHierarchy(roleHierarchy());
    return defaultWebSecurityExpressionHandler;
  }
  
  @Bean
  public RoleHierarchy roleHierarchy() {
  	RoleHierarchyImpl hierarchy = new RoleHierarchyImpl();
  	hierarchy.setHierarchy("ROLE_ADMIN > ROLE_MANAGER\n ROLE_MANAGER > ROLE_CLIENT\n ROLE_CLIENT > ROLE_ANONYMOUS");
  	return hierarchy;
  }

}
