package dev.fun.store.backend.mapper;

import java.util.List;

import org.mapstruct.DecoratedWith;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import dev.fun.store.backend.domain.User;
import dev.fun.store.backend.dto.UserDto;

@Mapper
@DecoratedWith(UserMapperDecorator.class)
public interface UserMapper {
	
	UserMapper MAPPER = Mappers.getMapper(UserMapper.class);
	
	User toUser(UserDto dto);
	
	@InheritInverseConfiguration
	UserDto fromUser(User user);
	
	List<User> toUserList(List<UserDto> dtoList);
	
	List<UserDto> fromUserList(List<User> userList);
	
}
