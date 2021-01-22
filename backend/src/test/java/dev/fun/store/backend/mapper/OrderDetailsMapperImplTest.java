package dev.fun.store.backend.mapper;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dev.fun.store.backend.domain.Product;
import dev.fun.store.backend.domain.order.OrderDetails;
import dev.fun.store.backend.dto.OrderDetailsDto;

class OrderDetailsMapperImplTest {
	
	OrderDetailsMapper mapper;
	
	@BeforeEach
	void setup() {
		mapper = new OrderDetailsMapperImpl();
	}

	@Test
	void testFromOrderDetails() {
		Product product = new Product("product", 100L);
		product.setId(1L);
		OrderDetails orderDetails = new OrderDetails();
		orderDetails.setProduct(product);
		orderDetails.setAmount(2);
		orderDetails.setTotalCost(200L);
		
		OrderDetailsDto actual = mapper.fromOrderDetails(orderDetails);
		
		assertNotNull(actual);
		assertEquals(orderDetails.getId(), actual.getId());
		assertEquals(orderDetails.getProduct().getId(), actual.getProductId());
		assertEquals(orderDetails.getProduct().getTitle(), actual.getProductTitle());
		assertEquals(orderDetails.getProduct().getCost(), actual.getProductCost());
		assertEquals(orderDetails.getAmount(), actual.getAmount());
		assertEquals(orderDetails.getTotalCost(), actual.getTotalCost());
	}

	@Test
	void testFromOrderDetailsList() {
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
		
		List<OrderDetailsDto> actual = mapper.fromOrderDetailsList(Arrays.asList(orderDetails1, orderDetails2));
		
		assertNotNull(actual);
		assertEquals(2, actual.size());
	}

}
