package dev.fun.store.backend.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import dev.fun.store.backend.domain.order.Order;
import dev.fun.store.backend.dto.OrderDto;

@Mapper
public interface OrderMapper {

	OrderMapper MAPPER = Mappers.getMapper(OrderMapper.class);
	
	@Mapping(target = "userId", source = "user.id")
	OrderDto fromOrder(Order order);
	
	List<OrderDto> fromOrderList(List<Order> orderList);
	
}
