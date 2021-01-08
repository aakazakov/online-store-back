package dev.fun.store.backend.mapper;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import dev.fun.store.backend.domain.User;
import dev.fun.store.backend.dto.UserDto;

class UserMapperDecoratorTest {
	
	class UserMapperDecoratorExt extends UserMapperDecorator {
		public UserMapperDecoratorExt(UserMapper delegate) {
			super(delegate);
		}

		@Override
		public User toUser(UserDto dto) {
			return toUser(dto);
		}

		@Override
		public List<User> toUserList(List<UserDto> dtoList) {
			return toUserList(dtoList);
		}	
	}
	
	UserMapper userMapper;
	UserMapperDecoratorExt decorator;
	
	static List<User> userList = new ArrayList<>();
	static List<UserDto> dtoList = new ArrayList<>();
	
	@BeforeAll
	static void initList() {
		User u1 = new User("u1", "p1", true); u1.setId(1L);
		User u2 = new User("u2", "p2", true); u2.setId(2L);
		User u3 = new User("u3", "p3", true); u3.setId(3L);
		
		userList.addAll(Arrays.asList(u1, u2, u3));
		
		UserDto dto1 = new UserDto();
		dto1.setId(u1.getId());
		dto1.setLogin(u1.getLogin());
		dto1.setPassword(u1.getPassword());
		
		UserDto dto2 = new UserDto();
		dto2.setId(u2.getId());
		dto2.setLogin(u2.getLogin());
		dto2.setPassword(u2.getPassword());
		
		UserDto dto3 = new UserDto();
		dto3.setId(u3.getId());
		dto3.setLogin(u3.getLogin());
		dto3.setPassword(u3.getPassword());
		
		dtoList.addAll(Arrays.asList(dto1, dto2, dto3));
	}
	
	@BeforeEach
	void init() {
		userMapper = Mockito.mock(UserMapper.class);
		decorator = new UserMapperDecoratorExt(userMapper);
	}
 	
	@Test
	void testFromUser() {
		User user = userList.get(0);
		UserDto dto = dtoList.get(0);
		
		Mockito.when(userMapper.fromUser(Mockito.any(User.class))).thenReturn(dto);
		
		UserDto actual = decorator.fromUser(user);
		
		assertNotNull(actual);
		assertEquals(UserMapperDecorator.STUB, actual.getPassword());
	}

	@Test
	void testFromUserList() {
		Mockito.when(userMapper.fromUserList(Mockito.anyList())).thenReturn(dtoList);
		
		List<UserDto> actual = decorator.fromUserList(userList);
		
		for (UserDto dto : actual) {
			assertNotNull(dto);
			assertEquals(UserMapperDecorator.STUB, dto.getPassword());
		}
	}

}
