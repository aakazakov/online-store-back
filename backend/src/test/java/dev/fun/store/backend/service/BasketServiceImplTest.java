package dev.fun.store.backend.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import dev.fun.store.backend.dao.BasketRepository;
import dev.fun.store.backend.dao.ProductRepository;
import dev.fun.store.backend.dao.UserRepository;
import dev.fun.store.backend.domain.Basket;
import dev.fun.store.backend.domain.Product;
import dev.fun.store.backend.domain.User;
import dev.fun.store.backend.dto.InputBasketDto;
import dev.fun.store.backend.dto.OutputBasketDto;
import dev.fun.store.backend.mapper.BasketMapper;
import dev.fun.store.backend.mapper.BasketMapperImpl;

class BasketServiceImplTest {

	BasketRepository basketRepository;
	UserRepository userRepository;
	ProductRepository productRepository;
	BasketMapper basketMapper;
	BasketServiceImpl basketService;
	
	@BeforeEach
	void setup() {
		basketRepository = Mockito.mock(BasketRepository.class);
		userRepository = Mockito.mock(UserRepository.class);
		productRepository = Mockito.mock(ProductRepository.class);
		basketMapper = new BasketMapperImpl();
		basketService = new BasketServiceImpl();
		basketService.setBasketRepository(basketRepository);
		basketService.setProductRepository(productRepository);
		basketService.setUserRepository(userRepository);
		basketService.setBasketMapper(basketMapper);
	}
	
	@Test
	void testCreateBasket() {
		Long productId = 1L;
		Long userId = 1L;
		Long basketId = 1L;
		
		Product product = new Product("product1", 10L);
		product.setId(productId);
		
		InputBasketDto inputBasketDto = new InputBasketDto();
		inputBasketDto.setBasketId(basketId);
		inputBasketDto.setUserId(userId);
		inputBasketDto.setProductId(Arrays.asList(productId, productId, productId));
		
		User user = new User("user", "pass", true);
		user.setId(userId);
		
		Basket basket = new Basket(user);
		basket.setId(basketId);
		basket.setProducts(Arrays.asList(product, product, product));
		
		Mockito.when(userRepository.getOne(Mockito.anyLong())).thenReturn(user);
		Mockito.when(productRepository.getOne(Mockito.anyLong())).thenReturn(product);
		Mockito.when(basketRepository.save(Mockito.any(Basket.class))).thenReturn(basket);
		
		OutputBasketDto actual = basketService.createBasket(inputBasketDto);
		
		assertNotNull(actual);
		assertEquals(basketId, actual.getBasketId());
		assertEquals(1, actual.getDetails().size());
		assertEquals(30L, actual.getTotalCost());
		assertEquals(3, actual.getTotalAmount());
	}

	@Test
	void testDeleteBasket() {
		Long id = 1L;
		
		basketService.deleteBasket(id);
		
		Mockito.verify(basketRepository).deleteById(id);
	}

}
