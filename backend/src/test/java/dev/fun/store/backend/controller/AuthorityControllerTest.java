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
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import dev.fun.store.backend.domain.authority.Auth;
import dev.fun.store.backend.domain.authority.Role;
import dev.fun.store.backend.dto.AuthorityDto;
import dev.fun.store.backend.dto.UserDto;
import dev.fun.store.backend.service.AuthorityServiceImpl;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AuthorityController.class)
class AuthorityControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	AuthorityServiceImpl authorityService;
	
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
	void testGetAllProducts() throws Exception {
		Mockito.when(authorityService.getAll()).thenReturn(authList);
		
		mockMvc
			.perform(MockMvcRequestBuilders.get("/authorities/all").accept(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(authList.size())));
	}

	@Test
	void testGetProduct() throws Exception {
		AuthorityDto dto = authList.get(0);
		Long id = dto.getId();
		
		Mockito.when(authorityService.getOne(id)).thenReturn(dto);
		
		mockMvc
			.perform(MockMvcRequestBuilders.get("/authorities/id/{id}", id).accept(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)));
	}

	@Test
	void testGetUsersByAuthorityId() throws Exception {
		Long id = 1L;
		Mockito.when(authorityService.getAllUsersByAuthorityId(id)).thenReturn(userList);
		
		mockMvc
			.perform(MockMvcRequestBuilders.get("/authorities/id/{id}/users", id).accept(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(userList.size())));
	}

	@Test
	void testGetAllAuthoritiesByUserId() throws Exception {
		Long id = 1L;
		List<AuthorityDto> auths = authList.subList(2, 3);
		Mockito.when(authorityService.getAllAuthoritiesByUserId(id)).thenReturn(auths);
		
		mockMvc
			.perform(MockMvcRequestBuilders.get("/authorities/all/user/{id}", id).accept(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(auths.size())));
	}

}