package dev.fun.store.backend.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.fun.store.backend.domain.order.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
	
	/**
	 * Finds all user orders by user ID
	 * @param userId
	 * @return {@code List} of {@link Order}
	 */
	List<Order> findByUserId(Long userId);

}
