package dev.fun.store.backend.controller;

import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dev.fun.store.backend.configuration.security.RestAuthenticationEntryPoint;
import dev.fun.store.backend.configuration.security.RestAuthenticationFailureHandler;
import dev.fun.store.backend.configuration.security.RestAuthenticationSuccessHandler;
import dev.fun.store.backend.configuration.security.RestHttpStatusReturningLogoutSuccessHandler;
import dev.fun.store.backend.dto.UserDto;
import dev.fun.store.backend.service.UserServiceImpl;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
class UserControllerTest {

	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@MockBean
	UserServiceImpl userService;
	
	@MockBean
	RestAuthenticationEntryPoint restAuthenticationEntryPoint;
	
	@MockBean
	RestAuthenticationSuccessHandler restAuthenticationSuccessHandler;
	
	@MockBean
	RestAuthenticationFailureHandler restAuthenticationFailureHandler;
	
	@MockBean
	RestHttpStatusReturningLogoutSuccessHandler restHttpStatusReturningLogoutSuccessHandler;
	
	static List<UserDto> userList = new ArrayList<>();
	
	@BeforeAll
	static void init() {
		UserDto u1 = new UserDto(); u1.setId(1L);
		UserDto u2 = new UserDto(); u2.setId(2L);
		UserDto u3 = new UserDto(); u3.setId(3L);
		UserDto u4 = new UserDto(); u4.setId(4L);
		
		userList.addAll(Arrays.asList(u1, u2, u3, u4));
	}
	
	@Test
	@WithMockUser(roles = {"MANAGER"})
	void testGetAllUsers() throws Exception {
		Mockito.when(userService.getAll()).thenReturn(userList);
		
		mockMvc
			.perform(MockMvcRequestBuilders.get("/users/all")
					.accept(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(userList.size())));
	}
	
	@Test
	@WithMockUser(username = "user", roles = {"CLIENT"})
	void testGetCurrentUser() throws Exception {
		UserDto user = userList.get(0);
		
		Mockito.when(userService.getByUsername(Mockito.anyString())).thenReturn(user);
		
		mockMvc
			.perform(MockMvcRequestBuilders.get("/users/current")
					.accept(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$.username", Matchers.is(user.getUsername())));
	}

	@Test
	@WithMockUser(roles = {"MANAGER"})
	void testGetUser() throws Exception {
		Long id = 1L;
		int index = 0;
		
		Mockito.when(userService.getOne(id)).thenReturn(userList.get(index));
		
		mockMvc
			.perform(MockMvcRequestBuilders.get("/users/id/{id}", id)
					.accept(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)));
	}

	@Test
	@WithMockUser(roles = {"ANONYMOUS"})
	void testAddClient() throws JsonProcessingException, Exception {
		UserDto dto = userList.get(0);
		
		Mockito.when(userService.saveClient(Mockito.any(UserDto.class))).thenReturn(dto);
		
		mockMvc
			.perform(MockMvcRequestBuilders.post("/users/add-client")
					.contentType(MediaType.APPLICATION_JSON_VALUE)
					.content(objectMapper.writeValueAsString(dto))
					.characterEncoding("utf-8")
					.with(csrf().asHeader()))
			.andExpect(status().isOk())
	    .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)));
	}

	@Test
	@WithMockUser(roles = {"CLIENT"})
	void testUpdateUser() throws JsonProcessingException, Exception {
		UserDto dto = userList.get(0);
		dto.setUsername("<(^=^)>");
		dto.setPassword("123");
		
		Mockito.when(userService.update(Mockito.any(UserDto.class))).thenReturn(dto);
		
		mockMvc
			.perform(MockMvcRequestBuilders.put("/users/update")
					.contentType(MediaType.APPLICATION_JSON_VALUE)
					.content(objectMapper.writeValueAsString(dto))
					.characterEncoding("utf-8")
					.with(csrf().asHeader()))
			.andExpect(status().isOk())
	    .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
			.andExpect(MockMvcResultMatchers.jsonPath("$.username", Matchers.is(dto.getUsername())));
	}

	@Test
	@WithMockUser(roles = {"ADMIN"})
	void testDeleteUser() throws Exception {
		Long id = 1L;
		
		mockMvc
			.perform(MockMvcRequestBuilders.delete("/users/delete/{id}", id)
					.accept(MediaType.APPLICATION_JSON_VALUE)
					.with(csrf().asHeader()))
			.andExpect(status().isOk());
		
		verify(userService).delete(id);
	}

}
