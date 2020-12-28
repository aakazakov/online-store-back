package dev.fun.store.backend.service;

import java.util.List;

import dev.fun.store.backend.dto.AuthorityDto;

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
		
}
