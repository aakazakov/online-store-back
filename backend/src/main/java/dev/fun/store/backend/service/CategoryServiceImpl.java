package dev.fun.store.backend.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import dev.fun.store.backend.dao.CategoryRepository;
import dev.fun.store.backend.domain.Category;
import dev.fun.store.backend.dto.CategoryDto;
import dev.fun.store.backend.mapper.CategoryMapper;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	private final CategoryRepository categoryRepository;
	
	public CategoryServiceImpl(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	@Override
	public CategoryDto getOne(Long id) {
		return CategoryMapper.MAPPER.fromCategory(categoryRepository.findById(id).orElse(null));
	}

	@Override
	public List<CategoryDto> getAll() {
		return CategoryMapper.MAPPER.fromCategoryList(categoryRepository.findAll());
	}

	@Override
	@Transactional
	public CategoryDto save(CategoryDto dto) {
		Category category = CategoryMapper.MAPPER.toCategory(dto);
		return CategoryMapper.MAPPER.fromCategory(categoryRepository.save(category));
	}

	@Override
	@Transactional
	public CategoryDto update(CategoryDto dto) {
		Long id = dto.getId();
		String newTitle = dto.getTitle();
		
		if (id == null)
			return null;
		
		Category category = categoryRepository.getOne(id);
		
		if (newTitle != null)
			category.setTitle(newTitle);
		
		return CategoryMapper.MAPPER.fromCategory(category);
	}

	@Override
	public void delete(Long id) {
		categoryRepository.deleteById(id);
	}

	@Override
	public List<CategoryDto> getAllByProductId(Long id) {
		return CategoryMapper.MAPPER.fromCategoryList(categoryRepository.findAllByProductId(id));
	}

}
