package dev.fun.store.backend.service;

import java.util.List;

import dev.fun.store.backend.domain.Product;

public interface ProductService {
	
	/**
	 * Returns the {@link Product} by its id
	 * @param id {@code Long} product id
	 * @return one {@link Product}
	 */
	Product getOne(Long id);
	
	/**
	 * Returns a {@code List} of all {@link Product}s
	 * @return all {@link Product}s
	 */
	List<Product> getAll();
	
	/**
	 * Used if need the product to be saved in a repository (e.g. DB)
	 * @param product {@link Product} to save
	 * @return saved {@link Product}
	 */
	Product save(Product product);
	
	/**
	 * Used to save the modified {@link Product} to a repository (e.g. DB)
	 * @param product modified {@link Product}
	 * @return saved {@link Product}
	 */
	Product update(Product product);
	
	/**
	 * Delete the {@link Product} by its id 
	 * @param id {@code Long} product id
	 */
	void delete(Long id);
	
}
