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
		Product p1 = testEntityManager.persist(new Product("prod1", 100L));
		Category c1 = testEntityManager.persist(new Category("cat1"));
		Category c2 = testEntityManager.persist(new Category("cat2"));
		Category c3 = testEntityManager.persist(new Category("cat3"));
		
		p1.setCategories(Arrays.asList(c1, c2));
		testEntityManager.persistAndFlush(p1);
		
		List<Category> actual = categoryRepository.findAllByProductId(p1.getId());
		
		assertNotNull(actual);
		assertEquals(2, actual.size());
		assertFalse(actual.contains(c3));
	}

}
