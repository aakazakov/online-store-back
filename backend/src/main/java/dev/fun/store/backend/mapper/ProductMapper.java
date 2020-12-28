package dev.fun.store.backend.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import dev.fun.store.backend.domain.Product;
import dev.fun.store.backend.dto.ProductDto;

@Mapper
public interface ProductMapper {

	ProductMapper MAPPER = Mappers.getMapper(ProductMapper.class);
	
	Product toProduct(ProductDto dto);
	
	@InheritInverseConfiguration
	ProductDto fromProduct(Product product);
	
	List<Product> toProductList(List<ProductDto> dtoList);
	
	List<ProductDto> fromProductList(List<Product> productList);
	
}
