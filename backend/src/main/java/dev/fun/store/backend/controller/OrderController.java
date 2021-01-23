package dev.fun.store.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.fun.store.backend.dto.OrderDto;
import dev.fun.store.backend.service.OrderService;

@RestController
@RequestMapping(path = "/orders", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderController {
	
	private final OrderService orderService;
	
	@Autowired
	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}

	@PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
	public OrderDto createOrder(@RequestBody OrderDto dto) {
		return orderService.createNewOrder(dto);
	}
	
	@GetMapping("/id/{id}")
	public OrderDto getOrder(@PathVariable(name = "id") Long id) {
		return orderService.getOrder(id);
	}
	
	@GetMapping("/all/user/{id}")
	public List<OrderDto> getUserOrders(@PathVariable(name = "id") Long userId) {
		return orderService.getUserOrders(userId);
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteOrder(@PathVariable(name = "id") Long id) {
		orderService.deleteOrder(id);
	}
	
	@PutMapping(path = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
	public OrderDto updateOrder(@RequestBody OrderDto dto) {
		return orderService.updateOrder(dto);
	}
	
}
