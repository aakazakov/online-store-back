package dev.fun.store.backend.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.fun.store.backend.dao.ProductRepository;
import dev.fun.store.backend.domain.Product;
import dev.fun.store.backend.dto.ProductDto;
import dev.fun.store.backend.mapper.ProductMapper;

@Service
public class ProductServiceImpl implements ProductService {
	
	private final ProductRepository productRepository;
	@Autowired
	public ProductServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	public ProductDto getOne(Long id) {
		return ProductMapper.MAPPER.fromProduct(productRepository.findById(id).orElse(new Product()));
	}

	@Override
	public List<ProductDto> getAll() {
		return ProductMapper.MAPPER.fromProductList(productRepository.findAll());
	}

	@Override
	@Transactional
	public ProductDto save(ProductDto dto) {
		Product product = ProductMapper.MAPPER.toProduct(dto);
		return ProductMapper.MAPPER.fromProduct(productRepository.save(product));
	}

	@Override
	@Transactional
	public ProductDto update(ProductDto dto) {
		// TODO
		return null;
	}

	@Override
	public void delete(Long id) {
		productRepository.deleteById(id);
	}

	@Override
	public List<ProductDto> getProductsByCategory(Long categoryId) {
		return ProductMapper.MAPPER.fromProductList(productRepository.findAllProductsByCategoryId(categoryId));
	}

}
