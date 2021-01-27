package dev.fun.store.backend.controller;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dev.fun.store.backend.configuration.security.RestAuthenticationEntryPoint;
import dev.fun.store.backend.configuration.security.RestAuthenticationFailureHandler;
import dev.fun.store.backend.configuration.security.RestAuthenticationSuccessHandler;
import dev.fun.store.backend.configuration.security.RestHttpStatusReturningLogoutSuccessHandler;
import dev.fun.store.backend.dto.ProductDto;
import dev.fun.store.backend.service.ProductServiceImpl;
import dev.fun.store.backend.service.UserService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ProductController.class)
class ProductControllerTest {

	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@MockBean
	ProductServiceImpl productService;
	
	@MockBean
	UserService userService;
	
	@MockBean
	RestAuthenticationEntryPoint restAuthenticationEntryPoint;
	
	@MockBean
	RestAuthenticationSuccessHandler restAuthenticationSuccessHandler;
	
	@MockBean
	RestAuthenticationFailureHandler restAuthenticationFailureHandler;
	
	@MockBean
	RestHttpStatusReturningLogoutSuccessHandler restHttpStatusReturningLogoutSuccessHandler;
	
	static List<ProductDto> productList = new ArrayList<>();
	
	@BeforeAll
	static void init() {
		ProductDto p1 = new ProductDto(); p1.setId(1L); p1.setTitle("p1"); p1.setCost(100L);
		ProductDto p2 = new ProductDto(); p2.setId(2L); p2.setTitle("p2"); p2.setCost(200L);
		ProductDto p3 = new ProductDto(); p3.setId(3L); p3.setTitle("p3"); p3.setCost(300L);
		ProductDto p4 = new ProductDto(); p4.setId(4L); p4.setTitle("p4"); p4.setCost(400L);
		ProductDto p5 = new ProductDto(); p5.setId(5L); p5.setTitle("p5"); p5.setCost(500L);
		
		productList.addAll(Arrays.asList(p1, p2, p3, p4, p5));
	}
	
	@Test
	@WithMockUser(roles = {"ANONYMOUS"})
	void testGetAllProducts() throws Exception {
		Mockito.when(productService.getAll()).thenReturn(productList);
		
		mockMvc
			.perform(MockMvcRequestBuilders
					.get("/products/all")
					.accept(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(productList.size())));
	}

	@Test
	@WithMockUser(roles = {"ANONYMOUS"})
	void testGetProduct() throws Exception {
		Long id = 1L;
		int index = 0;
		
		Mockito.when(productService.getOne(id)).thenReturn(productList.get(index));
		
		mockMvc
			.perform(MockMvcRequestBuilders
					.get("/products/id/{id}", id)
					.accept(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)));
	}

	@Test
	@WithMockUser(roles = {"ADMIN"})
	void testAddProduct() throws JsonProcessingException, Exception {
		ProductDto p6 = new ProductDto(); p6.setId(6L); p6.setTitle("p6"); p6.setCost(600L);
		
		Mockito.when(productService.save(Mockito.any(ProductDto.class))).thenReturn(p6);
		
		mockMvc
			.perform(MockMvcRequestBuilders.post("/products/add")
					.contentType(MediaType.APPLICATION_JSON_VALUE)
					.content(objectMapper.writeValueAsString(p6))
					.characterEncoding("utf-8"))
			.andExpect(status().isOk())
      .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(6)));
	}

	@Test
	@WithMockUser(roles = {"ADMIN"})
	void testUpdateProduct() throws JsonProcessingException, Exception {
		ProductDto dto = productList.get(0);
		dto.setCost(999L);
		
		Mockito.when(productService.update(Mockito.any(ProductDto.class))).thenReturn(dto);
		
		mockMvc
			.perform(MockMvcRequestBuilders.put("/products/update")
					.contentType(MediaType.APPLICATION_JSON_VALUE)
					.content(objectMapper.writeValueAsString(dto))
					.characterEncoding("utf-8"))
			.andExpect(status().isOk())
	    .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
			.andExpect(MockMvcResultMatchers.jsonPath("$.cost", Matchers.is(999)));
	}

	@Test
	@WithMockUser(roles = {"ADMIN"})
	void testDeleteProduct() throws Exception {
		Long id = 1L;
		
		mockMvc
			.perform(MockMvcRequestBuilders.delete("/products/delete/{id}", id)
					.accept(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(status().isOk());
		
		verify(productService).delete(id);
	}
	
	@Test
	@WithMockUser(roles = {"ANONYMOUS"})
	void testGetAllProductsByCategory() throws Exception {
		Long id = 1L;
		List<ProductDto> products = productList.subList(0, 3);
		
		Mockito.when(productService.getProductsByCategory(id)).thenReturn(products);
		
		mockMvc
			.perform(MockMvcRequestBuilders.get("/products/all/category/{id}", id)
					.accept(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(products.size())));
	}

}
