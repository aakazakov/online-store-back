package dev.fun.store.backend.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import dev.fun.store.backend.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

	/**
	 * Returns a {@code List} of all users with the concrete authority
	 * @param id {@code Long} authority ID
	 * @return {@code List} of {@link User}
	 */
	@Query(
			value = "SELECT u.id, u.login, u.password, u.enabled "
						+ "FROM users u "
						+ "JOIN users_authorities ua ON u.id=ua.user_id "
						+ "WHERE ua.authority_id=?1",
			nativeQuery = true)
	List<User> findUsersByAuthorityId(Long id);
	
}
