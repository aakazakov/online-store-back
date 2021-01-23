package dev.fun.store.backend.service;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import dev.fun.store.backend.dao.OrderRepository;
import dev.fun.store.backend.dao.UserRepository;
import dev.fun.store.backend.domain.Basket;
import dev.fun.store.backend.domain.Product;
import dev.fun.store.backend.domain.User;
import dev.fun.store.backend.domain.order.Order;
import dev.fun.store.backend.domain.order.OrderDetails;
import dev.fun.store.backend.domain.order.OrderStatus;
import dev.fun.store.backend.dto.OrderDto;
import dev.fun.store.backend.mapper.OrderDetailsMapperImpl;
import dev.fun.store.backend.mapper.OrderMapper;
import dev.fun.store.backend.mapper.OrderMapperImpl;

class OrderServiceImplTest {
	
	OrderRepository orderRepository;
	UserRepository userRepository;
	OrderMapper orderMapper;
	OrderService orderService;
	
	Order order;
	
	@BeforeEach
	void init() {
		orderRepository = Mockito.mock(OrderRepository.class);
		userRepository = Mockito.mock(UserRepository.class);
		orderMapper = new OrderMapperImpl(new OrderDetailsMapperImpl());
		orderService = new OrderServiceImpl(orderRepository, userRepository, orderMapper);		
	}
	
	@BeforeEach
	void setup() {
		User user = new User("user", "pass", true);
		user.setId(1L);
		
		Product product1 = new Product("p1", 100L);
		product1.setId(1L);
		Product product2 = new Product("p2", 200L);
		product2.setId(2L);
		List<Product> productList = Arrays.asList(product1, product2);
		
		Basket basket = new Basket(user);
		basket.setProducts(productList);
		user.setBasket(basket);
		
		order = new Order(OrderStatus.CREATED, LocalDateTime.now());
		order.setUser(user);
		
		order.setDeliveryAddress("5 steps forward, 3 steps right, turn left, then 10 jumps back");
		order.setId(1L);
		
		OrderDetails orderDetails1 = new OrderDetails();
		orderDetails1.setId(1L);
		orderDetails1.setOrder(order);
		orderDetails1.setAmount(2);
		orderDetails1.setProduct(product1);
		orderDetails1.setTotalCost(orderDetails1.getAmount() * product1.getCost());
		OrderDetails orderDetails2 = new OrderDetails();
		orderDetails2.setId(2L);
		orderDetails2.setOrder(order);
		orderDetails2.setAmount(3);
		orderDetails2.setProduct(product2);
		orderDetails2.setTotalCost(orderDetails2.getAmount() * product2.getCost());
		List<OrderDetails> orderDetailsList = Arrays.asList(orderDetails1, orderDetails2);
		
		order.setOrderDetails(orderDetailsList);		
		order.setTotalCost(orderDetails1.getTotalCost() + orderDetails2.getTotalCost());
		
		OrderDto orderDto = new OrderDto();
		orderDto.setUserId(user.getId());
		orderDto.setDeliveryAddress(order.getDeliveryAddress());
	}

	@Test
	void testCreateNewOrder() {
		User user = order.getUser();
		
		OrderDto orderDto = new OrderDto();
		orderDto.setUserId(user.getId());
		orderDto.setDeliveryAddress(order.getDeliveryAddress());
		
		Mockito.when(userRepository.getOne(Mockito.anyLong())).thenReturn(user);
		Mockito.when(orderRepository.save(Mockito.any(Order.class))).thenReturn(order);
		
		OrderDto actual = orderService.createNewOrder(orderDto);
		
		assertNotNull(actual);
		assertEquals(order.getId(), actual.getId());
		assertEquals(order.getOrderDetails().size(), actual.getOrderDetails().size());
		assertEquals(user.getId(), actual.getUserId());
	}

	@Test
	void testGetOrder() {
		Mockito.when(orderRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(order));
		
		OrderDto actual = orderService.getOrder(order.getId());
		
		assertNotNull(actual);
		assertEquals(order.getId(), actual.getId());
	}

	@Disabled
	@Test
	void testUpdateOrder() {
		fail("Not yet implemented");
	}

	@Test
	void testDeleteOrder() {
		orderService.deleteOrder(Mockito.anyLong());
		
		Mockito.verify(orderRepository).deleteById(Mockito.anyLong());
	}

	@Test
	void testGetUserOrders() {
		Mockito.when(orderRepository.findByUserId(Mockito.anyLong())).thenReturn(Arrays.asList(order));
		
		List<OrderDto> actual = orderService.getUserOrders(1L);
		
		assertNotNull(actual);
		assertEquals(order.getId(), actual.get(0).getId());
	}

}
