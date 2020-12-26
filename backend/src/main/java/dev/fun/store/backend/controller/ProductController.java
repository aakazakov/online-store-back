package dev.fun.store.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import dev.fun.store.backend.dto.ProductDto;
import dev.fun.store.backend.service.ProductService;
import dev.fun.store.backend.service.ProductServiceImpl;

@Controller
@RequestMapping("/products")
public class ProductController {
	
	private ProductService productService;
	
	@Autowired
	public ProductController(ProductServiceImpl productService) {
		this.productService = productService;
	}
	
	@GetMapping(path = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ProductDto> getAllProducts() {
		return productService.getAll();
	}
	
	@GetMapping(path = "/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ProductDto getProduct(@PathVariable(name = "id") Long id) {
		return productService.getOne(id);
	}
	
	@PostMapping(path = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
	public ProductDto addProduct(ProductDto dto) {
		return productService.save(dto);
	}
	
	@PutMapping(path = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
	public ProductDto updateProduct(ProductDto dto) {
		return productService.update(dto);
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteProduct(@PathVariable(name = "id") Long id) {
		productService.delete(id);
	}
	
}
