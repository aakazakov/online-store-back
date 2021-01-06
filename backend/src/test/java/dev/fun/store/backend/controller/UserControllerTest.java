package dev.fun.store.backend.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
	void testGetAllUsers() throws Exception {
		Mockito.when(userService.getAll()).thenReturn(userList);
		
		mockMvc
			.perform(MockMvcRequestBuilders.get("/users/all")
					.accept(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(userList.size())));
	}

	@Test
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
	void testAddClient() throws JsonProcessingException, Exception {
		UserDto dto = userList.get(0);
		
		Mockito.when(userService.saveClient(Mockito.any(UserDto.class))).thenReturn(dto);
		
		mockMvc
			.perform(MockMvcRequestBuilders.post("/users/add-client")
					.contentType(MediaType.APPLICATION_JSON_VALUE)
					.content(objectMapper.writeValueAsString(dto))
					.characterEncoding("utf-8"))
			.andExpect(status().isOk())
	    .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)));
	}

	@Disabled
	@Test
	void testUpdateUser() {
		fail("Not yet implemented");
	}

	@Disabled
	@Test
	void testDeleteUser() {
		fail("Not yet implemented");
	}

}
