package dev.fun.store.backend.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import dev.fun.store.backend.domain.Category;
import dev.fun.store.backend.dto.CategoryDto;

@Mapper
public interface CategoryMapper {

	CategoryMapper MAPPER = Mappers.getMapper(CategoryMapper.class);
	
	Category toCategory(CategoryDto dto);
	
	@InheritInverseConfiguration
	CategoryDto fromCategory(Category category);
	
	List<Category> toCategoryList(List<CategoryDto> dtoList);
	
	List<CategoryDto> fromCategoryList(List<Category> categoryList);
	
}
