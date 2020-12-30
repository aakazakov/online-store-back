package dev.fun.store.backend.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import dev.fun.store.backend.dao.AuthorityRepository;
import dev.fun.store.backend.dao.UserRepository;
import dev.fun.store.backend.domain.User;
import dev.fun.store.backend.domain.authority.Auth;
import dev.fun.store.backend.domain.authority.Authority;
import dev.fun.store.backend.domain.authority.Role;
import dev.fun.store.backend.dto.AuthorityDto;
import dev.fun.store.backend.dto.UserDto;

class AuthorityServiceImplTest {
	
	static List<Authority> authList;
	static List<User> userList;
	
	AuthorityRepository authorityRepository;
	UserRepository userRepository;
	AuthorityServiceImpl authorityService;
	
	@BeforeAll
	static void init() {
		authList = new ArrayList<>();
		Authority a1 = new Authority(Role.ADMIN);	a1.setId(1L);
		Authority a2 = new Authority(Role.MANAGER); a2.setId(2L);
		Authority a3 = new Authority(Role.CLIENT); a3.setId(3L);
		Authority a4 = new Authority(Auth.ADD_COMMENTS); a4.setId(4L);
		authList.addAll(Arrays.asList(a1, a2, a3, a4));
		
		userList = new ArrayList<>();
		User u1 = new User("login1", "pass1", true);
		User u2 = new User("login2", "pass2", true);
		User u3 = new User("login3", "pass3", true);
		User u4 = new User("login4", "pass4", true);
		userList.addAll(Arrays.asList(u1, u2, u3, u4));
	}
	
	@BeforeEach
	void setup() {
		authorityRepository = Mockito.mock(AuthorityRepository.class);
		userRepository = Mockito.mock(UserRepository.class);
		authorityService = new AuthorityServiceImpl(authorityRepository, userRepository);
	}

	@Test
	void testGetOne() {
		Long id = 3L;
		
		Mockito.when(authorityRepository.findById(id)).thenReturn(Optional.of(authList.get(2)));
		AuthorityDto actual = authorityService.getOne(id);
		
		assertNotNull(actual);
		assertEquals(id, actual.getId());
	}

	@Test
	void testGetAll() {
		Mockito.when(authorityRepository.findAll()).thenReturn(authList);
		List<AuthorityDto> actual = authorityService.getAll();
		
		assertNotNull(actual);
		assertEquals(authList.size(), actual.size());
	}

	@Test
	void testGetAllUsersByAuthorityId() {
		Long id = 2L;
		List<User> managers = new ArrayList<>();
		managers.add(userList.get(2));
		managers.add(userList.get(3));
		
		Mockito.when(userRepository.findUsersByAuthorityId(id)).thenReturn(managers);
		List<UserDto> actual = authorityService.getAllUsersByAuthorityId(id);
		
		assertNotNull(actual);
		assertEquals(managers.size(), actual.size());
	}

	@Test
	void testGetAllAuthoritiesByUserId() {
		Long id = 4L;
		List<Authority> auth = new ArrayList<>();
		auth.add(authList.get(2));
		auth.add(authList.get(3));
		
		Mockito.when(authorityRepository.findAuthoritiesByUserId(id)).thenReturn(auth);
		List<AuthorityDto> actual = authorityService.getAllAuthoritiesByUserId(id);
		
		assertNotNull(actual);
		assertEquals(auth.size(), actual.size());
	}

}
