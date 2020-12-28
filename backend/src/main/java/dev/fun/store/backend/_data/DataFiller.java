package dev.fun.store.backend._data;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import dev.fun.store.backend.dao.AuthorityRepository;
import dev.fun.store.backend.dao.CategoryRepository;
import dev.fun.store.backend.dao.ProductRepository;
import dev.fun.store.backend.dao.UserRepository;
import dev.fun.store.backend.domain.Category;
import dev.fun.store.backend.domain.Product;
import dev.fun.store.backend.domain.User;
import dev.fun.store.backend.domain.authority.Auth;
import dev.fun.store.backend.domain.authority.Authority;
import dev.fun.store.backend.domain.authority.Role;


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
	
	@Autowired
	AuthorityRepository authorityRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		// users
		{
			User user1 = new User("user1", "pass1", true);
			User user2 = new User("user2", "pass2", true);
			User user3 = new User("user3", "pass3", true);
			User user4 = new User("user4", "pass4", true);
			User user5 = new User("user5", "pass5", true);
			
			userRepository.saveAll(Arrays.asList(user1, user2, user3, user4, user5));
		}
		
		// products
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
		
		// authorities
		{
			Authority admin = new Authority(Role.ADMIN);
			Authority manager = new Authority(Role.MANAGER);
			Authority client = new Authority(Role.CLIENT);
			Authority addComment = new Authority(Auth.ADD_COMMENTS);
			Authority anonimous = new Authority(Role.ANONYMOUS);
			
			authorityRepository.saveAll(Arrays.asList(admin, manager, client, addComment, anonimous));
		}
		
		
		//categories
		{
			Category category1 = new Category("category1");
			Category category2 = new Category("category3");
			Category category3 = new Category("category3");
			
			categoryRepository.saveAll(Arrays.asList(category1, category2, category3));
		}
		
		// users_authorities
		{
			Authority admin = authorityRepository.findById(1L).orElse(null);
			Authority manager = authorityRepository.findById(2L).orElse(null);
			Authority client = authorityRepository.findById(3L).orElse(null);
			Authority addComment = authorityRepository.findById(4L).orElse(null);
			
			User user1 = userRepository.findById(1L).orElse(null);
			User user2 = userRepository.findById(2L).orElse(null);
			User user3 = userRepository.findById(3L).orElse(null);
			User user4 = userRepository.findById(4L).orElse(null);
			User user5 = userRepository.findById(5L).orElse(null);
			
			user1.getAuthorities().add(admin);
			user2.getAuthorities().add(manager);
			user3.getAuthorities().addAll(Arrays.asList(client, addComment));
			user4.getAuthorities().addAll(Arrays.asList(client, addComment));
			user5.getAuthorities().addAll(Arrays.asList(client, addComment));
			
			userRepository.saveAll(Arrays.asList(user1, user2, user3, user4, user5));
		}
		
		// products_categories
		{
			Category category1 = categoryRepository.findById(1L).orElse(null);
			Category category2 = categoryRepository.findById(2L).orElse(null);
			Category category3 = categoryRepository.findById(3L).orElse(null);
			
			Product product1 = productRepository.findById(1L).orElse(null);
			Product product2 = productRepository.findById(2L).orElse(null);
			Product product3 = productRepository.findById(3L).orElse(null);
			Product product4 = productRepository.findById(4L).orElse(null);
			Product product5 = productRepository.findById(5L).orElse(null);
			Product product6 = productRepository.findById(6L).orElse(null);
			Product product7 = productRepository.findById(7L).orElse(null);
			
			category1.getProducts().addAll(Arrays.asList(product1, product2, product3, product4));
			category2.getProducts().addAll(Arrays.asList(product5, product6));
			category3.getProducts().add(product7);
			
			categoryRepository.saveAll(Arrays.asList(category1, category2, category3));
		}
		
	}
	
}
