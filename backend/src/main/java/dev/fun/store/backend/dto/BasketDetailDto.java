package dev.fun.store.backend.dto;

import lombok.Data;

@Data
public class BasketDetailDto {

	private ProductDto productDto;
	private Long amount;
	private Long totalCost;
	
}
