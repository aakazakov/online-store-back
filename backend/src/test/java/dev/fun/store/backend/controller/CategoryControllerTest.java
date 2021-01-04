package dev.fun.store.backend.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import dev.fun.store.backend.dto.CategoryDto;
import dev.fun.store.backend.service.CategoryServiceImpl;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CategoryController.class)
class CategoryControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	
  @Autowired
  ObjectMapper objectMapper;
	
	@MockBean
	CategoryServiceImpl categoryService;
	
	static List<CategoryDto> categorylist = new ArrayList<>();
	
	@BeforeAll
	static void init() {
		CategoryDto c1 = new CategoryDto(); c1.setId(1L);
		CategoryDto c2 = new CategoryDto(); c2.setId(2L);
		CategoryDto c3 = new CategoryDto(); c3.setId(3L);
		
		categorylist.addAll(Arrays.asList(c1, c2, c3));
	}

	@Test
	void testGetAllCategories() throws Exception {
		Mockito.when(categoryService.getAll()).thenReturn(categorylist);
		
		mockMvc
			.perform(MockMvcRequestBuilders.get("/categories/all").accept(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(categorylist.size())));
	}

	@Test
	void testGetCategory() throws Exception {
		CategoryDto dto = categorylist.get(0);
		Long id = dto.getId();
		
		Mockito.when(categoryService.getOne(id)).thenReturn(dto);
		
		mockMvc
		.perform(MockMvcRequestBuilders.get("/categories/id/{id}", id).accept(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)));
	}

	@Test
	void testAddCategory() throws Exception {
		CategoryDto c4 = new CategoryDto(); c4.setTitle("c4"); c4.setId(4L);
		
		Mockito.when(categoryService.save(Mockito.any(CategoryDto.class))).thenReturn(c4);
		
		mockMvc
			.perform(MockMvcRequestBuilders.post("/categories/add")
					.contentType(MediaType.APPLICATION_JSON_VALUE)
					.content(objectMapper.writeValueAsString(c4))
					.characterEncoding("utf-8"))
			.andExpect(status().isOk())
      .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(4)));
	}

	@Disabled
	@Test
	void testUpdateCategory() {
		fail("Not yet implemented");
	}

	@Test
	void testDeleteCategory() throws Exception {
		Long id = 1L;
		mockMvc
			.perform(MockMvcRequestBuilders.delete("/categories/delete/{id}", id)
					.accept(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(status().isOk());
		
		verify(categoryService).delete(id);
	}
	
	@Test
	void testGetAllCategoriesOfProduct() throws Exception {
		Long id = 1L;
		List<CategoryDto> catList = categorylist.subList(0, 2);
		
		Mockito.when(categoryService.getAllByProductId(id)).thenReturn(catList);
		
		mockMvc
			.perform(MockMvcRequestBuilders.get("/categories/all/product/{id}", id)
					.accept(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(catList.size())));
	}

}
