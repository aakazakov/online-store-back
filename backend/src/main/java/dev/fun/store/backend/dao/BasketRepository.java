package dev.fun.store.backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.fun.store.backend.domain.Basket;

public interface BasketRepository extends JpaRepository<Basket, Long> {

}
