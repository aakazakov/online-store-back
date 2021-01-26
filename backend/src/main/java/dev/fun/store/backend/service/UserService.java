package dev.fun.store.backend.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import dev.fun.store.backend.dto.UserDto;

public interface UserService extends UserDetailsService {
	
	/**
	 * Returns the user by its ID
	 * @param id {@code Long} user ID
	 * @return one {@link UserDto}
	 */
	UserDto getOne(Long id);
	
	/**
	 * Returns a list of all users
	 * @return {@code List} of {@link UserDto}
	 */
	List<UserDto> getAll();
	
	/**
	 * Used if need the user to be saved in a repository (e.g. DB) as a client
	 * @param dto {@link UserDto} data to be saved
	 * @return saved user as {@link UserDto}
	 */
	UserDto saveClient(UserDto dto);
	
	/**
	 * Used to save the modified user to a repository (e.g. DB)
	 * @param dto {@link UserDto} modified user
	 * @return saved user as {@link UserDto}
	 */
	UserDto update(UserDto dto);
	
	/**
	 * Delete the user by its ID
	 * @param id {@code Long} user ID
	 */
	void delete(Long id);
	
}
