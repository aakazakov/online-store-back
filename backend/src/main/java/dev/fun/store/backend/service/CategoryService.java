package dev.fun.store.backend.service;

import java.util.List;

import dev.fun.store.backend.dto.CategoryDto;

public interface CategoryService {

	/**
	 * Returns the category by its id
	 * @param id {@code Long} category id
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
	 * @param dto category {@link CategoryDto} to save
	 * @return saved category {@link CategoryDto}
	 */
	CategoryDto save(CategoryDto dto);
	
	/**
	 * Used to save the modified category to a repository (e.g. DB)
	 * @param dto modified category {@link CategoryDto}
	 * @return saved category {@link CategoryDto}
	 */
	CategoryDto update(CategoryDto dto);
	
	/**
	 * Delete the category by its id
	 * @param id {@code Long} category id
	 */
	void delete(Long id);
	
	/**
	 * Returns all categories of product
	 * @param id product id
	 * @return a list of {@link CategoryDto}
	 */
	List<CategoryDto> getAllByProductId(Long id);
	
}
