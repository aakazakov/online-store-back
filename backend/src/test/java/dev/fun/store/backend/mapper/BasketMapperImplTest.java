package dev.fun.store.backend.mapper;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import dev.fun.store.backend.domain.Basket;
import dev.fun.store.backend.domain.User;
import dev.fun.store.backend.dto.OutputBasketDto;
import dev.fun.store.backend.domain.Product;

class BasketMapperImplTest {

	@Test
	void testFromBasket() {
		User user = new User();
		user.setId(1L);
		user.setUsername("login");
		user.setPassword("password");
		user.setEnabled(true);
		
		Product product1 = new Product();
		product1.setId(1L);
		product1.setCost(10L);
		product1.setTitle("product1");
		Product product2 = new Product();
		product2.setId(2L);
		product2.setCost(20L);
		product2.setTitle("product2");
		Product product3 = new Product();
		product3.setId(3L);
		product3.setCost(30L);
		product3.setTitle("product3");
		
		Basket basket = new Basket();
		basket.setId(1L);
		basket.setUser(user);
		basket.setProducts(Arrays.asList(product1, product2, product3, product3));
		
		BasketMapper basketMapper = new BasketMapperImpl();
		
		OutputBasketDto actual = basketMapper.fromBasket(basket);
		
		assertNotNull(actual);
		assertEquals(basket.getId(), actual.getBasketId());
		assertEquals(basket.getProducts().size(), actual.getTotalAmount());
		assertEquals(90L, actual.getTotalCost());
		assertNotNull(actual.getDetails());
		assertEquals(3, actual.getDetails().size());
		
	}

}
