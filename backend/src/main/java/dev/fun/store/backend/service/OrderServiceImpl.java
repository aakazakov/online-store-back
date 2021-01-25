package dev.fun.store.backend.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

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
public class OrderServiceImpl implements OrderService{
	
	private final OrderRepository orderRepository;
	private final UserRepository userRepository;
	private final OrderMapper orderMapper;
	
	@Autowired
	public OrderServiceImpl(OrderRepository orderRepository, UserRepository userRepository, OrderMapper orderMapper) {
		this.orderRepository = orderRepository;
		this.userRepository = userRepository;
		this.orderMapper = orderMapper;
	}

	@Override
	@Transactional
	public OrderDto createNewOrder(OrderDto dto) {
		Order order = new Order();
		
		User user = userRepository.getOne(dto.getUserId());
		
		List<Product> productList = user.getBasket().getProducts();
		
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
				.map(set -> {
					OrderDetails od = new OrderDetails();
					od.setOrder(order);
					od.setAmount(set.getValue());
					od.setProduct(set.getKey());
					od.setTotalCost(set.getKey().getCost() * set.getValue());
					return od;
				}).collect(Collectors.toList());
		
		
		Long totalCost = orderDetailsList.stream()
				.map(OrderDetails::getTotalCost)
				.reduce(0L, Long::sum);
		
		order.setDeliveryAddress(dto.getDeliveryAddress());
		order.setUser(user);
		order.setOrderDetails(orderDetailsList);
		order.setTotalCost(totalCost);
		order.setStatus(OrderStatus.CREATED);
		
		return orderMapper.fromOrder(orderRepository.save(order));
	}

	@Override
	public OrderDto getOrder(Long id) {
		return orderMapper.fromOrder(orderRepository.findById(id).orElse(new Order()));
	}

	@Override
	@Transactional
	public OrderDto updateOrder(OrderDto dto) {
		Long id = dto.getId();
		
		if (id == null)
			return null;
		
		Order order = orderRepository.getOne(id);
		
		if (dto.getDeliveryAddress() != null)
			order.setDeliveryAddress(dto.getDeliveryAddress());
		if (dto.getStatus() != null)
			order.setStatus(dto.getStatus());
		if (dto.getTotalCost() != null)
			order.setTotalCost(dto.getTotalCost());
		
		return orderMapper.fromOrder(orderRepository.save(order));
	}

	@Override
	public void deleteOrder(Long id) {
		orderRepository.deleteById(id);
	}

	@Override
	public List<OrderDto> getUserOrders(Long userId) {
		return orderMapper.fromOrderList(orderRepository.findByUserId(userId));
	}

}
