package dev.fun.store.backend.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import dev.fun.store.backend.domain.authority.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Long>{
	
	/**
	 * Returns a {@code List} of all authorities belonging to specific user
	 * @param id {@code Long} user id
	 * @return {@code List} of {@link Authority}s
	 */
	@Query(
			value = "SELECT a.id, a.title "
						+ "FROM authorities a "
						+ "JOIN users_authorities ua "
						+ "ON a.id = ua.authority_id "
						+ "WHERE ua.user_id=?1",
			nativeQuery = true)
	List<Authority> findAuthoritiesByUserId(Long id);
			
}
