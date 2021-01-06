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

import dev.fun.store.backend.domain.User;
import dev.fun.store.backend.domain.authority.Auth;
import dev.fun.store.backend.domain.authority.Authority;
import dev.fun.store.backend.domain.authority.Role;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class AuthorityRepositoryTest {
	
	@Autowired
	TestEntityManager testEntityManager;
	
	@Autowired
	AuthorityRepository authorityRepository;

	@Test
	void testFindAuthoritiesByUserId() {
		User testUser = testEntityManager.persist(new User("login", "pass", true));
		Authority testAuth1 = testEntityManager.persist(new Authority(Role.CLIENT));
		Authority testAuth2 = testEntityManager.persist(new Authority(Auth.ADD_COMMENTS));
		testEntityManager.persist(new Authority(Role.ADMIN));

		testUser.setAuthorities(Arrays.asList(testAuth1, testAuth2));
		testEntityManager.persistAndFlush(testUser);
		
		List<Authority> actual = authorityRepository.findAuthoritiesByUserId(testUser.getId());
		
		assertNotNull(actual);
		assertEquals(2, actual.size());
		assertTrue(actual.contains(testAuth1));
		assertTrue(actual.contains(testAuth2));
	}

}
