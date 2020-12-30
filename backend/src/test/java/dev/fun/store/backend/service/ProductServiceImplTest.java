package dev.fun.store.backend.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import dev.fun.store.backend.dao.ProductRepository;
import dev.fun.store.backend.domain.Product;
import dev.fun.store.backend.dto.ProductDto;

class ProductServiceImplTest {
	
	ProductRepository productRepository;
	ProductServiceImpl productService;
	
	List<Product> prodList = new ArrayList<>();
	
	@BeforeEach
	void init() {
		productRepository = Mockito.mock(ProductRepository.class);
		productService = new ProductServiceImpl(productRepository);
		
		Product p1 = new Product("p1", 10.10); p1.setId(1L);
		Product p2 = new Product("p2", 20.20); p2.setId(2L);
		Product p3 = new Product("p3", 30.30); p3.setId(3L);
		prodList.addAll(Arrays.asList(p1, p2, p3));
	}

	@Test
	void testGetOne() {
		Long id = 1L;
		
		Mockito.when(productRepository.findById(id)).thenReturn(Optional.of(prodList.get(0)));
		ProductDto actual = productService.getOne(id);
		
		assertNotNull(actual);
		assertEquals(id, actual.getId());
	}

	@Test
	void testGetAll() {
		Mockito.when(productRepository.findAll()).thenReturn(prodList);
		List<ProductDto> actual = productService.getAll();
		
		assertNotNull(actual);
		assertEquals(prodList.size(), actual.size());
	}

	@Test
	void testSave() {
		ProductDto dto = new ProductDto();
		dto.setTitle("p4");
		
		Mockito.when(productRepository.save(Mockito.any(Product.class))).then(invocation -> {
			Product p = invocation.getArgument(0);
			p.setId(4L);
			prodList.add(p);
			return p;
		});
		
		ProductDto actual = productService.save(dto);
		
		assertNotNull(actual);
		assertEquals(dto.getTitle(), actual.getTitle());
		assertEquals(4L, actual.getId());
		assertEquals(4, prodList.size());
	}

	@Disabled
	@Test
	void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	void testDelete() {
		Long id = 3L;
		int index = 2;
		int size = prodList.size();
		
		Mockito.doAnswer(invokation -> {
			prodList.remove(index);
			return null;
		}).when(productRepository).deleteById(id);
		
		productService.delete(id);
		
		assertEquals(size - 1, prodList.size());
	}

}
