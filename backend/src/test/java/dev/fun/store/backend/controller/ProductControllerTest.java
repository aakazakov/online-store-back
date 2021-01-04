package dev.fun.store.backend.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import dev.fun.store.backend.dto.ProductDto;
import dev.fun.store.backend.service.ProductServiceImpl;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ProductController.class)
class ProductControllerTest {

	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@MockBean
	ProductServiceImpl productService;
	
	static List<ProductDto> productList = new ArrayList<>();
	
	@BeforeAll
	static void init() {
		
	}
	
	@Test
	void testGetAllProducts() {
		fail("Not yet implemented");
	}

	@Disabled
	@Test
	void testGetProduct() {
		fail("Not yet implemented");
	}

	@Disabled
	@Test
	void testAddProduct() {
		fail("Not yet implemented");
	}

	@Disabled
	@Test
	void testUpdateProduct() {
		fail("Not yet implemented");
	}

	@Disabled
	@Test
	void testDeleteProduct() {
		fail("Not yet implemented");
	}

}
