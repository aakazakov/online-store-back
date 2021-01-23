package dev.fun.store.backend.controller;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
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
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dev.fun.store.backend.dto.OrderDto;
import dev.fun.store.backend.service.OrderServiceImpl;

@ExtendWith(SpringExtension.class)
@WebMvcTest(OrderController.class)
class OrderControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	
  @Autowired
  ObjectMapper objectMapper;
  
  @MockBean
  OrderServiceImpl orderService;
  
  List<OrderDto> orderDtoList = new ArrayList<>();
  
  @BeforeEach
  void setup() {
  	OrderDto dto1 = new OrderDto(); dto1.setId(1L);
  	OrderDto dto2 = new OrderDto(); dto2.setId(2L);
  	OrderDto dto3 = new OrderDto(); dto3.setId(3L);
  	
  	orderDtoList.addAll(Arrays.asList(dto1, dto2, dto3));
  }

	@Test
	void testCreateOrder() throws JsonProcessingException, Exception {
		OrderDto dto = orderDtoList.get(0);
		
		Mockito.when(orderService.createNewOrder(Mockito.any(OrderDto.class))).thenReturn(dto);
		
		mockMvc
			.perform(MockMvcRequestBuilders.post("/orders/create")
					.contentType(MediaType.APPLICATION_JSON_VALUE)
					.content(objectMapper.writeValueAsString(dto))
					.characterEncoding("utf-8"))
			.andExpect(status().isOk())
		  .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)));
	}
	
	@Test
	void testGetOrder() throws Exception {
		OrderDto dto = orderDtoList.get(0);
		
		Mockito.when(orderService.getOrder(Mockito.anyLong())).thenReturn(dto);
		
		mockMvc
			.perform(MockMvcRequestBuilders.get("/orders/id/{id}", 1L)
					.accept(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)));
	}
	
	@Test
	void testDeleteOrder() throws Exception {
		Long id = 1L;
		
		mockMvc
			.perform(MockMvcRequestBuilders.delete("/orders/delete/{id}", id)
					.accept(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(status().isOk());
		
		verify(orderService).deleteOrder(id);
	}
	
	@Test
	void testGetUserOrders() throws Exception {
		Long id = 1L;
		
		Mockito.when(orderService.getUserOrders(Mockito.anyLong())).thenReturn(orderDtoList);
		
		mockMvc
			.perform(MockMvcRequestBuilders.get("/orders/all/user/{id}", id)
					.accept(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(orderDtoList.size())));
	}
	
	@Test
	void testUpdateOrder() throws JsonProcessingException, Exception {
		OrderDto dto = orderDtoList.get(0);
		
		Mockito.when(orderService.updateOrder(Mockito.any(OrderDto.class))).thenReturn(dto);
		
		mockMvc
			.perform(MockMvcRequestBuilders.put("/orders/update")
					.contentType(MediaType.APPLICATION_JSON_VALUE)
					.content(objectMapper.writeValueAsString(dto))
					.characterEncoding("utf-8"))
			.andExpect(status().isOk())
		  .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)));
	}

}
