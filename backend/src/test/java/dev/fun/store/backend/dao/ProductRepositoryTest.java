package dev.fun.store.backend.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import dev.fun.store.backend.domain.Category;
import dev.fun.store.backend.domain.Product;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class ProductRepositoryTest {
	
	@Autowired
	TestEntityManager testEntityManager;
	
	@Autowired
	ProductRepository productRepository;

	@Test
	void testFindAllProductsByCategoryId() {
		{
			Category c1 = new Category("cat1");
			Product p1 = new Product("product1", 100L);
			Product p2 = new Product("product2", 200L);
			Product p3 = new Product("product3", 300L);
			
			testEntityManager.persist(c1);
			testEntityManager.persist(p1);
			testEntityManager.persist(p2);
			testEntityManager.persist(p3);
		}
		
		Category c1 = testEntityManager.find(Category.class, 1L);
		Product p1 = testEntityManager.find(Product.class, 1L);
		Product p2 = testEntityManager.find(Product.class, 2L);
		List<Product> products = new ArrayList<>(Arrays.asList(p1, p2));
		c1.setProducts(products);
		testEntityManager.persistAndFlush(c1);
		
		List<Product> actual = productRepository.findAllProductsByCategoryId(c1.getId());
		
		assertNotNull(actual);
		assertEquals(products, actual);
	}
	
}
