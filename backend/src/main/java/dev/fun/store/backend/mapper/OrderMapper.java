package dev.fun.store.backend.mapper;

import java.util.List;

import dev.fun.store.backend.domain.order.Order;
import dev.fun.store.backend.dto.OrderDto;

public interface OrderMapper {
	
	/**
	 * Converts {@link Order} to {@link OrderDto}
	 * @param order {@link Order}
	 * @return {@link OrderDto}
	 */
	OrderDto fromOrder(Order order);
	
	/**
	 * Converts from {@code List} of {@link Order} to {@code List} of {@link OrderDto}
	 * @param orderList {@code List} of {@link Order}
	 * @return {@code List} of {@link OrderDto}
	 */
	List<OrderDto> fromOrderList(List<Order> orderList);
	
}
