package dev.fun.store.backend.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import dev.fun.store.backend.domain.User;
import dev.fun.store.backend.domain.order.Order;
import dev.fun.store.backend.domain.order.OrderStatus;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class OrderRepositoryTest {
	
	@Autowired
	TestEntityManager testEntityManager;
	
	@Autowired
	OrderRepository orderRepository;

	@Test
	void testFindByUserId() {
		
		User user = testEntityManager.persist(new User("user", "pass", true));
		
		Order order1 = new Order(OrderStatus.CREATED, LocalDateTime.now());
		order1.setUser(user);
		Order order2 = new Order(OrderStatus.CREATED, LocalDateTime.now());
		order2.setUser(user);
	
		testEntityManager.persistAndFlush(order1);
		testEntityManager.persistAndFlush(order2);
		
		List<Order> actual = orderRepository.findByUserId(user.getId());
		
		assertNotNull(actual);
		assertEquals(2, actual.size());
		
	}

}
