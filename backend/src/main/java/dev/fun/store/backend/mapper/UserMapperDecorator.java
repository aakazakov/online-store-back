package dev.fun.store.backend.mapper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import dev.fun.store.backend.domain.User;
import dev.fun.store.backend.dto.UserDto;

public abstract class UserMapperDecorator implements UserMapper {
	
	public static final String STUB = "";

	private UserMapper delegate;
	
	@Autowired
	public UserMapperDecorator(UserMapper delegate) {
		this.delegate = delegate;
	}

	// Resetting the password passed to UserDto
	@Override
	public UserDto fromUser(User user) {
		UserDto dto = delegate.fromUser(user);
		dto.setPassword(STUB);
		return dto;
	}
	
	// Resetting the password passed to UserDto
	@Override
	public List<UserDto> fromUserList(List<User> userList) {
		List<UserDto> dtoList = delegate.fromUserList(userList);
		for (UserDto dto : dtoList) {
			dto.setPassword(STUB);
		}
		return dtoList;
	}
	
}
