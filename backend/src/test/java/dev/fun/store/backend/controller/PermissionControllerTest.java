package dev.fun.store.backend.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import dev.fun.store.backend.configuration.security.RestAuthenticationEntryPoint;
import dev.fun.store.backend.configuration.security.RestAuthenticationFailureHandler;
import dev.fun.store.backend.configuration.security.RestAuthenticationSuccessHandler;
import dev.fun.store.backend.configuration.security.RestHttpStatusReturningLogoutSuccessHandler;
import dev.fun.store.backend.service.UserService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PermissionController.class)
class PermissionControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	
  @Autowired
  ObjectMapper objectMapper;
  
	@MockBean
	UserService userService;
	
	@MockBean
	RestAuthenticationEntryPoint restAuthenticationEntryPoint;
	
	@MockBean
	RestAuthenticationSuccessHandler restAuthenticationSuccessHandler;
	
	@MockBean
	RestAuthenticationFailureHandler restAuthenticationFailureHandler;
	
	@MockBean
	RestHttpStatusReturningLogoutSuccessHandler restHttpStatusReturningLogoutSuccessHandler;

	@Test
	@WithMockUser(roles = {"CLIENT"})
	void testIsAuth() throws Exception {
		
		mockMvc
			.perform(MockMvcRequestBuilders.get("/permit/is-auth")
					.contentType(MediaType.APPLICATION_JSON_VALUE)
					.characterEncoding("utf-8"))
			.andExpect(status().isOk())
		  .andExpect(MockMvcResultMatchers.jsonPath("$.auth", Matchers.is(true))); 		
	}

	@Test
	@WithMockUser(roles = {"CLIENT"})
	void testCsrf() throws Exception {
		mockMvc
			.perform(MockMvcRequestBuilders.get("/permit/csrf")
					.contentType(MediaType.APPLICATION_JSON_VALUE)
					.characterEncoding("utf-8"))
			.andExpect(status().isOk());
	}

}
