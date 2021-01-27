package dev.fun.store.backend.controller;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
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
import dev.fun.store.backend.dto.BasketDetailDto;
import dev.fun.store.backend.dto.InputBasketDto;
import dev.fun.store.backend.dto.OutputBasketDto;
import dev.fun.store.backend.dto.ProductDto;
import dev.fun.store.backend.service.BasketServiceImpl;
import dev.fun.store.backend.service.UserService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(BasketController.class)
class BasketControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	
  @Autowired
  ObjectMapper objectMapper;
  
  @MockBean
  BasketServiceImpl basketService;
  
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
  
  InputBasketDto inputBasketDto;
  OutputBasketDto outputBasketDto;
  
	@BeforeEach
	void setup() {
		inputBasketDto = new InputBasketDto();
		outputBasketDto = new OutputBasketDto();
		
		ProductDto productDto1 = new ProductDto();
		productDto1.setId(1L);
		productDto1.setTitle("product1");
		productDto1.setCost(10L);
		ProductDto productDto2 = new ProductDto();
		productDto2.setId(2L);
		productDto2.setTitle("product2");
		productDto2.setCost(20L);
			
		Long userId = 1L;
		Long basketId = 1L;
		List<Long> productId = Arrays.asList(productDto1.getId(), productDto2.getId());
		
		BasketDetailDto basketDetailDto1 = new BasketDetailDto();
		Integer amount = 2;
		basketDetailDto1.setProductDto(productDto1);
		basketDetailDto1.setAmount(amount);
		basketDetailDto1.setTotalCost(productDto1.getCost() * amount);
		
		BasketDetailDto basketDetailDto2 = new BasketDetailDto();
		amount = 1;
		basketDetailDto2.setProductDto(productDto2);
		basketDetailDto2.setAmount(amount);
		basketDetailDto2.setTotalCost(productDto2.getCost() * amount);
		
		inputBasketDto.setBasketId(basketId);
		inputBasketDto.setProductId(productId);
		inputBasketDto.setUserId(userId);
		
		outputBasketDto.setBasketId(basketId);
		outputBasketDto.setDetails(Arrays.asList(basketDetailDto1, basketDetailDto2));
		outputBasketDto.setTotalAmount(basketDetailDto1.getAmount() + basketDetailDto2.getAmount());
		outputBasketDto.setTotalCost(basketDetailDto1.getTotalCost() + basketDetailDto2.getTotalCost());
		
	}

	@Test
	@WithMockUser(roles = {"CLIENT"})
	void testCreateBasket() throws JsonProcessingException, Exception {
		Mockito.when(basketService.createBasket(Mockito.any(InputBasketDto.class))).thenReturn(outputBasketDto);
		
		mockMvc
			.perform(MockMvcRequestBuilders.post("/baskets/create")
					.contentType(MediaType.APPLICATION_JSON_VALUE)
					.content(objectMapper.writeValueAsString(inputBasketDto))
					.characterEncoding("utf-8"))
			.andExpect(status().isOk())
	    .andExpect(MockMvcResultMatchers.jsonPath("$.basketId", Matchers.is(1)));
	}
	
	@Test
	@WithMockUser(roles = {"CLIENT"})
	void testGetBasket() throws Exception {
		Mockito.when(basketService.getBasket(Mockito.anyLong())).thenReturn(outputBasketDto);
		
		mockMvc
			.perform(MockMvcRequestBuilders.get("/baskets/id/{id}", 1L).accept(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$.basketId", Matchers.is(1)));
	}
	
	@Test
	@WithMockUser(roles = {"CLIENT"})
	void testGetBasketById() throws Exception {
		Mockito.when(basketService.getBasketByUserId(Mockito.anyLong())).thenReturn(outputBasketDto);
		
		mockMvc
			.perform(MockMvcRequestBuilders.get("/baskets/user/{id}", 1L).accept(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$.basketId", Matchers.is(1)));
	}

	@Test
	@WithMockUser(roles = {"CLIENT"})
	void testDeleteBasket() throws Exception {
		Long id = 1L;
		
		mockMvc
			.perform(MockMvcRequestBuilders.delete("/baskets/delete/{id}", id)
					.accept(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(status().isOk());
		
		verify(basketService).deleteBasket(id);
	}
	
	@Test
	@WithMockUser(roles = {"CLIENT"})
	void testAddProducts() throws Exception {
		Mockito.when(basketService.addProducts(Mockito.any(InputBasketDto.class))).thenReturn(outputBasketDto);
		
		mockMvc
		.perform(MockMvcRequestBuilders.put("/baskets/add-products")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(inputBasketDto))
				.characterEncoding("utf-8"))
		.andExpect(status().isOk())
    .andExpect(MockMvcResultMatchers.jsonPath("$.basketId", Matchers.is(1)));		
	}
	
	@Test
	@WithMockUser(roles = {"CLIENT"})
	void testRemoveProducts() throws Exception {
		Mockito.when(basketService.removeProducts(Mockito.any(InputBasketDto.class))).thenReturn(outputBasketDto);
		
		mockMvc
		.perform(MockMvcRequestBuilders.put("/baskets/remove-products")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(inputBasketDto))
				.characterEncoding("utf-8"))
		.andExpect(status().isOk())
    .andExpect(MockMvcResultMatchers.jsonPath("$.basketId", Matchers.is(1)));		
	}

}
