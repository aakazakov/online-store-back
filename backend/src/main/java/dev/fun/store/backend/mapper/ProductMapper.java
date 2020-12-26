package dev.fun.store.backend.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import dev.fun.store.backend.domain.Product;
import dev.fun.store.backend.dto.ProductDto;
import dev.fun.store.backend.finance.Money;

@Mapper
public interface ProductMapper {

	ProductMapper MAPPER = Mappers.getMapper(ProductMapper.class);
	
	Product toProduct(ProductDto dto);
	
	@InheritInverseConfiguration
	@Mapping(source = "cost", target = "cost", qualifiedByName = "convertCostToDouble")
	ProductDto fromProduct(Product product);
	
	List<Product> toProductList(List<ProductDto> dtoList);
	
	List<ProductDto> fromProductList(List<Product> productList);
	
	@Named("convertCostToDouble")
	public static Double convertCostToDouble(Long cost) {
		return Money.longToDouble(cost);
	}
	
}
