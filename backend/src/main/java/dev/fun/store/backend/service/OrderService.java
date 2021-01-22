package dev.fun.store.backend.service;

import java.util.List;

import dev.fun.store.backend.dto.OrderDto;

public interface OrderService {

	/**
	 * Creates and saves a new order
	 * @param dto {@link OrderDto}
	 * @return {@link OrderDto}
	 */
	OrderDto createNewOrder(OrderDto dto);
	
	/**
	 * Returns the order by its ID
	 * @param id order ID
	 * @return {@link OrderDto}
	 */
	OrderDto getOrder(Long id);
	
	/**
	 * Saves the order with new parameters
	 * @param dto {@link OrderDto}
	 * @return {@link OrderDto}
	 */
	OrderDto updateOrder(OrderDto dto);
	
	/**
	 * Removes the order by its ID
	 * @param id order ID
	 */
	void deleteOrder(Long id);
	
	/**
	 * Returns all user orders
	 * @param userId user ID
	 * @return {@code List} of {@link OrderDto}
	 */
	List<OrderDto> getUserOrders(Long userId);
	
}
