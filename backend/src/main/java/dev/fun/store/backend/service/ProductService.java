package dev.fun.store.backend.service;

import java.util.List;

import dev.fun.store.backend.dto.ProductDto;

public interface ProductService {
	
	/**
	 * Returns the product by its id
	 * @param id {@code Long} product id
	 * @return one {@link ProductDto}
	 */
	ProductDto getOne(Long id);
	
	/**
	 * Returns a {@code List} of all products
	 * @return all {@link ProductDto}s
	 */
	List<ProductDto> getAll();
	
	/**
	 * Used if need the product to be saved in a repository (e.g. DB)
	 * @param dto product {@link ProductDto} to save
	 * @return saved product {@link ProductDto}
	 */
	ProductDto save(ProductDto dto);
	
	/**
	 * Used to save the modified product to a repository (e.g. DB)
	 * @param dto modified product {@link ProductDto}
	 * @return saved product {@link ProductDto}
	 */
	ProductDto update(ProductDto dto);
	
	/**
	 * Delete the product by its id
	 * @param id {@code Long} product id
	 */
	void delete(Long id);
	
	/**
	 * Returns a list of all products in the specified category
	 * @param id category id
	 * @return {@code List} of {@link ProductDto}
	 */
	List<ProductDto> getProductsByCategory(Long id);
	
}
