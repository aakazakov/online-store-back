package dev.fun.store.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
	
	private Long id;
	private String title;
	private Long cost;
	
	public ProductDto(String title, Long cost) {
		this.title = title;
		this.cost = cost;
	}
	
}
