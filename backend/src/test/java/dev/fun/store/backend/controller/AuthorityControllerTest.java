package dev.fun.store.backend.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
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
import dev.fun.store.backend.domain.authority.Auth;
import dev.fun.store.backend.domain.authority.Role;
import dev.fun.store.backend.dto.AuthorityDto;
import dev.fun.store.backend.dto.SetAuthorityDto;
import dev.fun.store.backend.dto.UserDto;
import dev.fun.store.backend.service.AuthorityServiceImpl;
import dev.fun.store.backend.service.UserService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AuthorityController.class)
class AuthorityControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@MockBean
	AuthorityServiceImpl authorityService;
	
	@MockBean
	private UserService userService;
	
	@MockBean
	private RestAuthenticationEntryPoint restAuthenticationEntryPoint;
	
	@MockBean
	private RestAuthenticationSuccessHandler restAuthenticationSuccessHandler;
	
	@MockBean
	private RestAuthenticationFailureHandler restAuthenticationFailureHandler;
	
	@MockBean
	private RestHttpStatusReturningLogoutSuccessHandler restHttpStatusReturningLogoutSuccessHandler;
	
	static List<AuthorityDto> authList = new ArrayList<>();
	static List<UserDto> userList = new ArrayList<>();
	
	@BeforeAll
	static void init() {
		AuthorityDto a1 = new AuthorityDto();	a1.setId(1L); a1.setTitle("ROLE_" + Role.ADMIN.name());
		AuthorityDto a2 = new AuthorityDto(); a2.setId(2L); a2.setTitle("ROLE_" + Role.MANAGER.name());
		AuthorityDto a3 = new AuthorityDto(); a3.setId(3L); a3.setTitle("ROLE_" + Role.CLIENT.name());
		AuthorityDto a4 = new AuthorityDto(); a4.setId(4L); a4.setTitle(Auth.ADD_COMMENTS.name());
		authList.addAll(Arrays.asList(a1, a2, a3, a4));
		
		UserDto u1 = new UserDto(); u1.setId(1L);
		UserDto u2 = new UserDto(); u2.setId(2L);
		UserDto u3 = new UserDto(); u3.setId(3L);
		userList.addAll(Arrays.asList(u1, u2, u3));
	}
	
	@Test
	@WithMockUser(roles = {"ADMIN"})
	void testGetAllAuthorities() throws Exception {
		Mockito.when(authorityService.getAll()).thenReturn(authList);
		
		mockMvc
			.perform(MockMvcRequestBuilders.get("/authorities/all").accept(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(authList.size())));
	}

	@Test
	@WithMockUser(roles = {"ADMIN"})
	void testGetAuthority() throws Exception {
		AuthorityDto dto = authList.get(0);
		Long id = dto.getId();
		
		Mockito.when(authorityService.getOne(id)).thenReturn(dto);
		
		mockMvc
			.perform(MockMvcRequestBuilders.get("/authorities/id/{id}", id).accept(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)));
	}

	@Test
	@WithMockUser(roles = {"ADMIN"})
	void testGetUsersByAuthorityId() throws Exception {
		Long id = 1L;
		
		Mockito.when(authorityService.getAllUsersByAuthorityId(id)).thenReturn(userList);
		
		mockMvc
			.perform(MockMvcRequestBuilders.get("/authorities/id/{id}/users", id).accept(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(userList.size())));
	}

	@Test
	@WithMockUser(roles = {"ADMIN"})
	void testGetAllAuthoritiesByUserId() throws Exception {
		Long id = 1L;
		List<AuthorityDto> auths = authList.subList(2, 3);
		
		Mockito.when(authorityService.getAllAuthoritiesByUserId(id)).thenReturn(auths);
		
		mockMvc
			.perform(MockMvcRequestBuilders.get("/authorities/all/user/{id}", id).accept(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(auths.size())));
	}
	
	@Test
	@WithMockUser(roles = {"ADMIN"})
	void testSetAuthorities() throws Exception {
		SetAuthorityDto dto = new SetAuthorityDto();
		List<AuthorityDto> authorities = authList.subList(0, 1);
		
		Mockito.when(authorityService.setAuthorities(Mockito.any(SetAuthorityDto.class))).thenReturn(authorities);
		
		mockMvc
			.perform(MockMvcRequestBuilders.put("/authorities/set")
					.contentType(MediaType.APPLICATION_JSON_VALUE)
					.content(objectMapper.writeValueAsString(dto))
					.characterEncoding("utf-8"))
			.andExpect(status().isOk())
	    .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)));
	}

}
