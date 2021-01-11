package dev.fun.store.backend.service;

import java.util.List;

import dev.fun.store.backend.dto.AuthorityDto;
import dev.fun.store.backend.dto.SetAuthorityDto;
import dev.fun.store.backend.dto.UserDto;

public interface AuthorityService {
	
	/**
	 * Returns the authority by its id
	 * @param id {@code Long} authority id
	 * @return one {@link AuthorityDto}
	 */
	AuthorityDto getOne(Long id);
	
	/**
	 * Returns a {@code List} of all authorities
	 * @return all {@link AuthorityDto}s
	 */
	List<AuthorityDto> getAll();
	
	/**
	 * Returns a {@code List} of all users with the concrete authority
	 * @param id {@code Long} authority id
	 * @return {@code List} of {@link UserDto}
	 */
	List<UserDto> getAllUsersByAuthorityId(Long id);
	
	/**
	 * Returns all user authorities
	 * @param id {@code Long} user id
	 * @return {@code List} of {@link AuthorityDto}
	 */
	List<AuthorityDto> getAllAuthoritiesByUserId(Long id);
	
	/**
	 * Sets the actual list of authorities for the user
	 * @param dto {@link SetAuthorityDto} contains {@code Long} userId and {@code List} of {@code Long} authorityId 
	 * @return {@code List} of {@link AuthorityDto}
	 */
	List<AuthorityDto> setAuthorities(SetAuthorityDto dto);
		
}
