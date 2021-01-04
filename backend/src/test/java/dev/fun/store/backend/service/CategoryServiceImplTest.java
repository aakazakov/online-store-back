package dev.fun.store.backend.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import dev.fun.store.backend.dao.CategoryRepository;
import dev.fun.store.backend.domain.Category;
import dev.fun.store.backend.dto.CategoryDto;

class CategoryServiceImplTest {
	
	CategoryRepository categoryRepository;
	CategoryServiceImpl categoryService;
	
	List<Category> catList = new ArrayList<>();
	
	@BeforeEach
	void setup() {
		categoryRepository = Mockito.mock(CategoryRepository.class);
		categoryService = new CategoryServiceImpl(categoryRepository);
		
		Category c1 = new Category("c1"); c1.setId(1L);
		Category c2 = new Category("c2"); c2.setId(2L);
		Category c3 = new Category("c3"); c3.setId(3L);
		catList.addAll(Arrays.asList(c1, c2, c3));
	}
	

	@Test
	void testGetOne() {
		Long id = 2L;
		
		Mockito.when(categoryRepository.findById(id)).thenReturn(Optional.of(catList.get(1)));
		CategoryDto actual = categoryService.getOne(id);
		
		assertNotNull(actual);
		assertEquals(id, actual.getId());
	}


	@Test
	void testGetAll() {
		Mockito.when(categoryRepository.findAll()).thenReturn(catList);
		List<CategoryDto> actual = categoryService.getAll();
		
		assertNotNull(actual);
		assertEquals(catList.size(), actual.size());
	}

	@Test
	void testSave() {
		CategoryDto dto = new CategoryDto();
		dto.setTitle("c4");
		
		Mockito.when(categoryRepository.save(Mockito.any(Category.class))).then(invocation -> {
			Category c = invocation.getArgument(0);
			c.setId(4L);
			catList.add(c);
			return c;
		});
		
		CategoryDto actual = categoryService.save(dto);
		
		assertNotNull(actual);
		assertEquals(dto.getTitle(), actual.getTitle());
		assertEquals(4L, actual.getId());
		assertEquals(4, catList.size());
	}

	@Disabled
	@Test
	void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	void testDelete() {
		Long id = 3L;
		int index = 2;
		int size = catList.size();
		
		Mockito.doAnswer(invocation -> {
			catList.remove(index);
			return null;
		}).when(categoryRepository).deleteById(id);
		
		categoryService.delete(id);
		
		assertEquals(size - 1, catList.size());
	}
	
	@Test
	void testGetAllByProductId() {
		Long id = 1L;
		int index = 0;
		
		List<CategoryDto> categoryList = new ArrayList<>();
		Category c = catList.get(index);
		
		CategoryDto dto = new CategoryDto();
		dto.setId(c.getId());
		dto.setTitle(c.getTitle());
		
		categoryList.add(dto);
		
		Mockito.when(categoryRepository.findAllByProductId(id)).thenReturn(catList.subList(index, index + 1));
		
		List<CategoryDto> actual = categoryService.getAllByProductId(id);
		
		assertNotNull(actual);
		assertEquals(categoryList, actual);
	}

}
