package dev.fun.store.backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.fun.store.backend.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

}
