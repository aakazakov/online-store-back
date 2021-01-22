package dev.fun.store.backend.mapper;

import java.util.List;

import dev.fun.store.backend.domain.order.OrderDetails;
import dev.fun.store.backend.dto.OrderDetailsDto;

public interface OrderDetailsMapper {
	
	/**
	 * Converts from {@link OrderDetails} to {@link OrderDetailsDto}
	 * @param orderDetails {@link OrderDetails}
	 * @return {@link OrderDetailsDto}
	 */
	OrderDetailsDto fromOrderDetails(OrderDetails orderDetails);
	
	/**
	 * Converts from {@code List} of {@link OrderDetails} to {@code List} of {@link OrderDetailsDto}
	 * @param orderDetailsList {@code List} of {@link OrderDetails}
	 * @return {@code List} of {@link OrderDetailsDto}
	 */
	List<OrderDetailsDto> fromOrderDetailsList(List<OrderDetails> orderDetailsList);
	
}
