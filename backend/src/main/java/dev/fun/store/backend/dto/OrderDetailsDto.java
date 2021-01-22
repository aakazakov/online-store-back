package dev.fun.store.backend.dto;

import lombok.Data;

@Data
public class OrderDetailsDto {

	private Long id;
	private Long productId;
	private Long productTitle;
	private Long productCost;
	private Integer amount;
	private Long totalCost;
	
}
