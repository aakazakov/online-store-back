package dev.fun.store.backend._data;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import dev.fun.store.backend.dao.ProductRepository;
import dev.fun.store.backend.dao.UserRepository;
import dev.fun.store.backend.domain.Product;
import dev.fun.store.backend.domain.User;


/**
 * Filling the database with fake entities.
 * <p>
 * If you want this to work, uncomment the annotation {@code @Component} and
 * it's {@code import}. If not, comment out. ))
 */
@Component
public class DataFiller implements CommandLineRunner {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ProductRepository productRepository;

	@Override
	public void run(String... args) throws Exception {
		
		{
			User user1 = new User("user1", "pass1", true);
			User user2 = new User("user2", "pass2", true);
			User user3 = new User("user3", "pass3", true);
			User user4 = new User("user4", "pass4", true);
			User user5 = new User("user5", "pass5", true);
			
			userRepository.saveAll(Arrays.asList(user1, user2, user3, user4, user5));
		}
		
		{
			Product product1 = new Product("product1", 100.0);
			Product product2 = new Product("product2", 200.0);
			Product product3 = new Product("product3", 300.0);
			Product product4 = new Product("product4", 400.0);
			Product product5 = new Product("product5", 500.0);
			Product product6 = new Product("product6", 600.0);
			Product product7 = new Product("product7", 700.95);
			
			productRepository.saveAll(Arrays.asList(product1, product2, product3, product4, product5, product6, product7));
		}
		
	}
	
}
