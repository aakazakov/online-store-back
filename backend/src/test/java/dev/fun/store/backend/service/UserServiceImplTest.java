package dev.fun.store.backend.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import dev.fun.store.backend.dao.UserRepository;
import dev.fun.store.backend.domain.User;
import dev.fun.store.backend.dto.UserDto;

class UserServiceImplTest {
	
	UserRepository userRepository;
	UserServiceImpl userService;
	
	List<User> userList = new ArrayList<>();
	
	@BeforeEach
	void init() {
		userRepository = Mockito.mock(UserRepository.class);
		userService = new UserServiceImpl(userRepository);
		
		User u1 = new User("login1", "pass1", true); u1.setId(1L);
		User u2 = new User("login2", "pass2", true); u2.setId(2L);
		User u3 = new User("login3", "pass3", true); u3.setId(3L);
		userList.addAll(Arrays.asList(u1, u2, u3));
	}

	@Test
	void testGetOne() {
		Long id = 1L;
		
		Mockito.when(userRepository.findById(id)).thenReturn(Optional.of(userList.get(0)));
		UserDto actual = userService.getOne(id);
		
		assertNotNull(actual);
		assertEquals(id, actual.getId());
	}

	@Test
	void testGetAll() {
		Mockito.when(userRepository.findAll()).thenReturn(userList);
		List<UserDto> actual = userService.getAll();
		
		assertNotNull(actual);
		assertEquals(userList.size(), actual.size());
	}

	@Disabled
	@Test
	void testSave() {
		fail("Not yet implemented");
	}

	@Disabled
	@Test
	void testUpdate() {
		fail("Not yet implemented");
	}

	@Disabled
	@Test
	void testDelete() {
		fail("Not yet implemented");
	}

}
