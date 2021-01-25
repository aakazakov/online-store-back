package dev.fun.store.backend.dto;

import java.util.List;

import dev.fun.store.backend.domain.order.OrderStatus;
import lombok.Data;

@Data
public class OrderDto {

	private Long id;
	private Long userId;
	private OrderStatus status;
	private String deliveryAddress;
	private String created;
	private String updated;
	private Long totalCost;
	private List<OrderDetailsDto> orderDetails;
	
}
