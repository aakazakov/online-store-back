package dev.fun.store.backend.service;

import java.util.List;

import dev.fun.store.backend.dto.CategoryDto;

public interface CategoryService {

	/**
	 * Returns the category by its ID
	 * @param id {@code Long} category ID
	 * @return one {@link CategoryDto}
	 */
	CategoryDto getOne(Long id);
	
	/**
	 * Returns a {@code List} of all categories
	 * @return all {@link CategoryDto}s
	 */
	List<CategoryDto> getAll();
	
	/**
	 * Used if need the category to be saved in a repository (e.g. DB)
	 * @param dto {@link CategoryDto} data to be saved
	 * @return saved category as {@link CategoryDto}
	 */
	CategoryDto save(CategoryDto dto);
	
	/**
	 * Used to save the modified category to a repository (e.g. DB)
	 * @param dto {@link CategoryDto} modified category
	 * @return saved category as {@link CategoryDto}
	 */
	CategoryDto update(CategoryDto dto);
	
	/**
	 * Delete the category by its ID
	 * @param id {@code Long} category ID
	 */
	void delete(Long id);
	
	/**
	 * Returns all categories of the product
	 * @param id product ID
	 * @return a list of {@link CategoryDto}
	 */
	List<CategoryDto> getAllByProductId(Long id);
	
}
