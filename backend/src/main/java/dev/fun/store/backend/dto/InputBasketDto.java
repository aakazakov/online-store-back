package dev.fun.store.backend.dto;

import java.util.List;

import lombok.Data;

@Data
public class InputBasketDto {
	
	private Long userId;
	private Long basketId;
	private List<Long> productId;
	
}
