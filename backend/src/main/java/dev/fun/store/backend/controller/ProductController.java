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

import dev.fun.store.backend.dto.ProductDto;
import dev.fun.store.backend.service.ProductService;
import dev.fun.store.backend.service.ProductServiceImpl;

@RestController
@RequestMapping(path = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {
	
	private ProductService productService;
	
	@Autowired
	public ProductController(ProductServiceImpl productService) {
		this.productService = productService;
	}
	
	@GetMapping("/all")
	public List<ProductDto> getAllProducts() {
		return productService.getAll();
	}
	
	@GetMapping("/id/{id}")
	public ProductDto getProduct(@PathVariable(name = "id") Long id) {
		return productService.getOne(id);
	}
	
	@PostMapping(path = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ProductDto addProduct(@RequestBody ProductDto dto) {
		return productService.save(dto);
	}
	
	@PutMapping(path = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ProductDto updateProduct(@RequestBody ProductDto dto) {
		return productService.update(dto);
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteProduct(@PathVariable(name = "id") Long id) {
		productService.delete(id);
	}
	
	@GetMapping("/all/category/{id}")
	public List<ProductDto> getAllProductsByCategory(@PathVariable(name = "id") Long categoryId) {
		return productService.getProductsByCategory(categoryId);
	}
	
}
