package dev.fun.store.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.fun.store.backend.dao.OrderRepository;
import dev.fun.store.backend.dao.UserRepository;
import dev.fun.store.backend.dto.OrderDto;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderDto getOrder(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteOrder(Long id) {
		orderRepository.deleteById(id);		
	}

}
