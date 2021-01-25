package dev.fun.store.backend.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import dev.fun.store.backend.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

	/**
	 * Returns a {@code List} of all product categories
	 * @param id {@code Long} product ID
	 * @return {@code List} of {@link Category}
	 */
	@Query(
			value = "SELECT c.id, c.title "
						+ "FROM categories c "
						+ "JOIN products_categories pc "
						+ "ON c.id=pc.category_id "
						+ "WHERE pc.product_id=?1",
			nativeQuery = true)
	List<Category> findAllByProductId(Long id);
	
}
