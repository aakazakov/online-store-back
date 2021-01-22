package dev.fun.store.backend.mapper;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dev.fun.store.backend.domain.Product;
import dev.fun.store.backend.domain.User;
import dev.fun.store.backend.domain.order.Order;
import dev.fun.store.backend.domain.order.OrderDetails;
import dev.fun.store.backend.domain.order.OrderStatus;
import dev.fun.store.backend.dto.OrderDto;

class OrderMapperImplTest {
	
	OrderMapper mapper;
	
	List<Order> orderList = new ArrayList<>();
	
	@BeforeEach
	void init() {
		OrderDetailsMapperImpl odm = new OrderDetailsMapperImpl();
		mapper = new OrderMapperImpl(odm);
		
		Order order = new Order(OrderStatus.CREATED, LocalDateTime.now());
		order.setId(1L);
		order.setDeliveryAddress("☡");
		
		User user = new User("user", "pass", true);
		user.setId(1L);
		order.setUser(user);
		
		Product product1 = new Product("product1", 100L);
		product1.setId(1L);
		OrderDetails orderDetails1 = new OrderDetails();
		orderDetails1.setProduct(product1);
		orderDetails1.setAmount(2);
		orderDetails1.setTotalCost(200L);
		
		Product product2 = new Product("product2", 200L);
		product2.setId(2L);
		OrderDetails orderDetails2 = new OrderDetails();
		orderDetails2.setProduct(product2);
		orderDetails2.setAmount(2);
		orderDetails2.setTotalCost(200L);
		
		order.setOrderDetails(Arrays.asList(orderDetails1, orderDetails2));
		
		order.setTotalCost(orderDetails1.getTotalCost() + orderDetails2.getTotalCost());
		
		Order order2 = new Order(OrderStatus.CREATED, LocalDateTime.now());
		order2.setId(2L);
		order2.setDeliveryAddress("☡");
		
		User user2 = new User("user2", "pass2", true);
		user2.setId(2L);
		order2.setUser(user2);
		
		Product product3 = new Product("product3", 100L);
		product3.setId(3L);
		OrderDetails orderDetails3 = new OrderDetails();
		orderDetails3.setProduct(product3);
		orderDetails3.setAmount(2);
		orderDetails3.setTotalCost(200L);
		
		Product product4 = new Product("product4", 200L);
		product4.setId(4L);
		OrderDetails orderDetails4 = new OrderDetails();
		orderDetails4.setProduct(product4);
		orderDetails4.setAmount(2);
		orderDetails4.setTotalCost(200L);
		
		order.setOrderDetails(Arrays.asList(orderDetails1, orderDetails2));		
		order.setTotalCost(orderDetails1.getTotalCost() + orderDetails2.getTotalCost());
		
		order2.setOrderDetails(Arrays.asList(orderDetails3, orderDetails4));	
		order2.setTotalCost(orderDetails3.getTotalCost() + orderDetails4.getTotalCost());
		
		orderList.addAll(Arrays.asList(order, order2));
	}

	@Test
	void testFromOrder() {
		Order order = orderList.get(0);
		
		OrderDto actual = mapper.fromOrder(order);
		
		assertNotNull(actual);
		assertEquals(order.getId(), actual.getId());
		assertEquals(order.getCreated(), actual.getCreated());
		assertEquals(order.getDeliveryAddress(), actual.getDeliveryAddress());
		assertEquals(order.getOrderDetails().size(), actual.getOrderDetails().size());
		assertEquals(order.getTotalCost(), actual.getTotalCost());
		assertEquals(order.getUser().getId(), actual.getUserId());
		
	}

	@Test
	void testFromOrderList() {
		List<OrderDto> actual = mapper.fromOrderList(orderList);
		
		assertNotNull(actual);
		assertEquals(orderList.size(), actual.size());
	}

}
