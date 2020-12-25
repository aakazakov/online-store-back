package dev.fun.store.backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.fun.store.backend.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
