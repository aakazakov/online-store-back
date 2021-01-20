package dev.fun.store.backend.service;

import dev.fun.store.backend.dto.OrderDto;

public interface OrderService {

	/**
	 * Creates a new order and saves it in the repository
	 * @param dto {@link OrderDto}
	 * @return {@link OrderDto}
	 */
	OrderDto createNewOrder(OrderDto dto);
	
	/**
	 * Returns the order by its ID
	 * @param id {@code Long} order ID
	 * @return {@link OrderDto}
	 */
	OrderDto getOrder(Long id);
	
	/**
	 * Removes the order by its ID
	 * @param id {@code Long} order ID
	 */
	void deleteOrder(Long id);
	
}
