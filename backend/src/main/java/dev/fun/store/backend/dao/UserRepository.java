package dev.fun.store.backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.fun.store.backend.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
