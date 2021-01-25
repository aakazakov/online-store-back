package dev.fun.store.backend.service;

import java.util.List;

import dev.fun.store.backend.dto.ProductDto;

public interface ProductService {
	
	/**
	 * Returns the product by its ID
	 * @param id {@code Long} product ID
	 * @return one {@link ProductDto}
	 */
	ProductDto getOne(Long id);
	
	/**
	 * Returns a {@code List} of all products
	 * @return {@link ProductDto} list
	 */
	List<ProductDto> getAll();
	
	/**
	 * Used if need the product to be saved in a repository (e.g. DB)
	 * @param dto {@link ProductDto} data to be saved
	 * @return saved product as {@link ProductDto}
	 */
	ProductDto save(ProductDto dto);
	
	/**
	 * Used to save the modified product to a repository (e.g. DB)
	 * @param dto {@link ProductDto} modified product
	 * @return saved product as {@link ProductDto}
	 */
	ProductDto update(ProductDto dto);
	
	/**
	 * Delete the product by its ID
	 * @param id {@code Long} product ID
	 */
	void delete(Long id);
	
	/**
	 * Returns a list of all products in the specified category
	 * @param id category ID
	 * @return {@code List} of {@link ProductDto}
	 */
	List<ProductDto> getProductsByCategory(Long id);
	
}
