package dev.fun.store.backend.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.fun.store.backend.dao.OrderRepository;
import dev.fun.store.backend.dao.UserRepository;
import dev.fun.store.backend.domain.Product;
import dev.fun.store.backend.domain.User;
import dev.fun.store.backend.domain.order.Order;
import dev.fun.store.backend.domain.order.OrderDetails;
import dev.fun.store.backend.domain.order.OrderStatus;
import dev.fun.store.backend.dto.OrderDto;
import dev.fun.store.backend.mapper.OrderMapper;

@Service
public class OrderServiceImpl implements OrderService {
	
	private final OrderRepository orderRepository;
	private final UserRepository userRepository;
	
	@Autowired
	public OrderServiceImpl(OrderRepository orderRepository, UserRepository userRepository) {
		this.orderRepository = orderRepository;
		this.userRepository = userRepository;
	}

	@Override
	public OrderDto createNewOrder(OrderDto dto) {
		User user = userRepository.getOne(dto.getUserId());
		List<Product> productList = user.getBasket().getProducts();
		Order order = new Order(OrderStatus.CREATED, LocalDateTime.now());
		order.setUser(user);
		
		Map<Product, Integer> productMap = new HashMap<>();
		for (Product p : productList) {
			if (productMap.containsKey(p)) {
				Integer amount = productMap.get(p);
				++amount;
				productMap.put(p, amount);
			} else {
				productMap.put(p, 1);
			}
		}
		
		List<OrderDetails> orderDetailsList = productMap.entrySet().stream()
				.map(pair -> {
					OrderDetails orderDetails = new OrderDetails();
					orderDetails.setOrder(order);
					orderDetails.setProduct(pair.getKey());
					orderDetails.setAmount(pair.getValue());
					orderDetails.setTotalCost(pair.getKey().getCost() * pair.getValue());
					return orderDetails;
				}).collect(Collectors.toList());
		
		// TODO add other code...
		
		return null;
	}

	@Override
	public OrderDto getOrder(Long id) {
		return OrderMapper.MAPPER.fromOrder(orderRepository.findById(id).orElse(new Order()));
	}

	@Override
	public void deleteOrder(Long id) {
		orderRepository.deleteById(id);		
	}

}
