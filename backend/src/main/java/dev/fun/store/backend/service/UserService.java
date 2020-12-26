package dev.fun.store.backend.service;

import java.util.List;

import dev.fun.store.backend.dto.UserDto;

public interface UserService {
	
	/**
	 * Returns the user by its id
	 * @param id {@code Long} user id
	 * @return one {@link UserDto}
	 */
	UserDto getOne(Long id);
	
	/**
	 * Returns a list of all users
	 * @return {@code List} of {@link UserDto}
	 */
	List<UserDto> getAll();
	
	/**
	 * Used if need the user to be saved in a repository (e.g. DB)
	 * @param dto user {@link UserDto} to save
	 * @return saved user {@link UserDto}
	 */
	UserDto save(UserDto dto);
	
	/**
	 * Used to save the modified user to a repository (e.g. DB)
	 * @param dto modified user {@link UserDto}
	 * @return saved user {@link UserDto}
	 */
	UserDto update(UserDto dto);
	
	/**
	 * Delete the user by its id 
	 * @param id {@code Long} user id
	 */
	void delete(Long id);
	
}
