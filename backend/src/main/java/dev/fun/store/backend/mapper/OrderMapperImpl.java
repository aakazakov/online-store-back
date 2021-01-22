package dev.fun.store.backend.mapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.fun.store.backend.domain.order.Order;
import dev.fun.store.backend.dto.OrderDetailsDto;
import dev.fun.store.backend.dto.OrderDto;

@Component
public class OrderMapperImpl implements OrderMapper {
	
	private final OrderDetailsMapper orderDetailsMapper;
	
	@Autowired
	public OrderMapperImpl(OrderDetailsMapperImpl orderDetailsMapper) {
		this.orderDetailsMapper = orderDetailsMapper;
	}

	@Override
	public OrderDto fromOrder(Order order) {
		OrderDto dto = new OrderDto();
		List<OrderDetailsDto> orderDetailsList = orderDetailsMapper.fromOrderDetailsList(order.getOrderDetails());
		
		LocalDateTime updated = order.getUpdated();
		if (updated != null)
			dto.setUpdated(updated);
		
		dto.setCreated(order.getCreated());
		dto.setStatus(order.getStatus());
		dto.setDeliveryAddress(order.getDeliveryAddress());
		dto.setId(order.getId());
		dto.setOrderDetails(orderDetailsList);
		dto.setTotalCost(order.getTotalCost());
		dto.setUserId(order.getUser().getId());
		
		return dto;
	}

	@Override
	public List<OrderDto> fromOrderList(List<Order> orderList) {
		return orderList.stream().map(this::fromOrder).collect(Collectors.toList());
	}

}
