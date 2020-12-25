package dev.fun.store.backend.service;

import java.util.List;

import dev.fun.store.backend.domain.User;

public interface UserService {
	
	/**
	 * Returns the {@link User} by its id
	 * @param id {@code Long} user id
	 * @return one {@link User}
	 */
	User getOne(Long id);
	
	/**
	 * Returns a {@code List} of all {@link User}s
	 * @return all {@link User}s
	 */
	List<User> getAll();
	
	/**
	 * Used if need the user to be saved in a repository (e.g. DB)
	 * @param user {@link User} to save
	 * @return saved {@link User}
	 */
	User save(User user);
	
	/**
	 * Used to save the modified {@link User} to a repository (e.g. DB)
	 * @param user modified {@link User}
	 * @return saved {@link User}
	 */
	User update(User user);
	
	/**
	 * Delete the {@link User} by its id 
	 * @param id {@code Long} user id
	 */
	void delete(Long id);
	
}
