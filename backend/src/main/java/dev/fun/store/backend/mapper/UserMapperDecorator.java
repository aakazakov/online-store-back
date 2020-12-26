package dev.fun.store.backend.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import dev.fun.store.backend.domain.User;
import dev.fun.store.backend.dto.UserDto;

public abstract class UserMapperDecorator implements UserMapper {

	@Autowired
	@Qualifier("delegate")
	private UserMapper delegate;
	
	// Resetting the password passed to UserDto
	@Override
	public UserDto fromUser(User user) {
		UserDto dto = delegate.fromUser(user);
		dto.setPassword(null);
		return dto;
	}
	
}
