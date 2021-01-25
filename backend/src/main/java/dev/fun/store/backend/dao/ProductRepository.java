package dev.fun.store.backend.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import dev.fun.store.backend.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	
	/**
	 * Returns all products in the specified category
	 * @param id {@code Long} category ID
	 * @return {@code List} of {@link Products}
	 */
	@Query(
			value = "SELECT p.id, p.cost, p.title "
						+ "FROM products p "
						+ "JOIN products_categories pc "
						+ "ON p.id=pc.product_id "
						+ "WHERE pc.category_id=?1",
			nativeQuery = true)
	List<Product> findAllProductsByCategoryId(Long id);

}
