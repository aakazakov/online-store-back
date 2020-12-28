package dev.fun.store.backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.fun.store.backend.domain.authority.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Long>{
	
}
