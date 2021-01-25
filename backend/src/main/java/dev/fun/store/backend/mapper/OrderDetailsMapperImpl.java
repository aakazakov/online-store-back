package dev.fun.store.backend.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import dev.fun.store.backend.domain.order.OrderDetails;
import dev.fun.store.backend.dto.OrderDetailsDto;

@Component
public class OrderDetailsMapperImpl implements OrderDetailsMapper {

	@Override
	public OrderDetailsDto fromOrderDetails(OrderDetails orderDetails) {
		if (orderDetails == null) {
			return null;
		}
		
		OrderDetailsDto dto = new OrderDetailsDto();
		
		dto.setId(orderDetails.getId());
		dto.setProductId(orderDetails.getProduct().getId());
		dto.setProductTitle(orderDetails.getProduct().getTitle());
		dto.setProductCost(orderDetails.getProduct().getCost());
		dto.setAmount(orderDetails.getAmount());
		dto.setTotalCost(orderDetails.getTotalCost());
		
		return dto;
	}

	@Override
	public List<OrderDetailsDto> fromOrderDetailsList(List<OrderDetails> orderDetailsList) {
		List<OrderDetailsDto> dtoList = new ArrayList<>();
		for (OrderDetails od : orderDetailsList) {
			dtoList.add(fromOrderDetails(od));
		}
		return dtoList;
	}

}
