package dev.fun.store.backend.dto;

import java.time.LocalDateTime;
import java.util.List;

import dev.fun.store.backend.domain.order.DeliveryAddress;
import dev.fun.store.backend.domain.order.OrderDetails;
import dev.fun.store.backend.domain.order.OrderStatus;
import lombok.Data;

@Data
public class OrderDto {
	
	private Long id;
	private Long userId;
	private DeliveryAddress address;
	private List<OrderDetails> details;
	private LocalDateTime created;
	private LocalDateTime updated;
	private Long totaCost;
	private Integer totalAmount;
	private OrderStatus status;
		
}
