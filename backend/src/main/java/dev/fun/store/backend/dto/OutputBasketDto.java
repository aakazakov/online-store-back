package dev.fun.store.backend.dto;

import java.util.List;

import lombok.Data;

@Data
public class OutputBasketDto {

	private Long basketId;
	private List<BasketDetailDto> details;
	private Integer totalAmount;
	private Long totalCost;
	
}
