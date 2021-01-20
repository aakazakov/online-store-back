package dev.fun.store.backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.fun.store.backend.domain.order.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
