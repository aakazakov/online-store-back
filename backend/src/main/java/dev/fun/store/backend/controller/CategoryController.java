package dev.fun.store.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.fun.store.backend.dto.CategoryDto;
import dev.fun.store.backend.service.CategoryService;
import dev.fun.store.backend.service.CategoryServiceImpl;

@RestController
@RequestMapping(path = "/categories", produces = MediaType.APPLICATION_JSON_VALUE)
public class CategoryController {

	private final CategoryService categoryService;

	@Autowired
	public CategoryController(CategoryServiceImpl categoryService) {
		this.categoryService = categoryService;
	}
	
	@GetMapping("/all")
	public List<CategoryDto> getAllCategories() {
		return categoryService.getAll();
	}
	
	@GetMapping("/id/{id}")
	public CategoryDto getCategory(@PathVariable(name = "id") Long id) {
		return categoryService.getOne(id);
	}
	
	@PostMapping(path = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
	public CategoryDto addCategory(@RequestBody CategoryDto dto) {
		return categoryService.save(dto);
	}
	
	@PutMapping(path = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
	public CategoryDto updateCategory(@RequestBody CategoryDto dto) {
		return categoryService.update(dto);
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteCategory(@PathVariable(name = "id") Long id) {
		categoryService.delete(id);
	}
	
	@GetMapping("/all/product/{id}")
	public List<CategoryDto> getAllCategoriesOfProduct(@PathVariable(name = "id") Long productId) {
		return categoryService.getAllByProductId(productId);
	}
	
}
