package dev.fun.store.backend.dao;

import static org.junit.jupiter.api.Assertions.*;

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
class CategoryRepositoryTest {
	
	@Autowired
	TestEntityManager testEntityManager;
	
	@Autowired
	CategoryRepository categoryRepository;

	@Test
	void testFindAllByProductId() {
		{
			Product p1 = new Product("prod1", 100L);
			
			Category c1 = new Category("cat1");
			Category c2 = new Category("cat2");
			Category c3 = new Category("cat3");
			
			testEntityManager.persist(p1);
			testEntityManager.persist(c1);
			testEntityManager.persist(c2);
			testEntityManager.persist(c3);
		}
		
		Product p1 = testEntityManager.find(Product.class, 1L);
		Category c1 = testEntityManager.find(Category.class, 1L);
		Category c2 = testEntityManager.find(Category.class, 2L);
		Category c3 = testEntityManager.find(Category.class, 3L);
		p1.setCategories(Arrays.asList(c1, c2));
		testEntityManager.persistAndFlush(p1);
		
		List<Category> actual = categoryRepository.findAllByProductId(p1.getId());
		
		assertNotNull(actual);
		assertEquals(2, actual.size());
		assertFalse(actual.contains(c3));
	}

}
