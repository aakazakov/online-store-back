package dev.fun.store.backend.mapper;

import dev.fun.store.backend.domain.Basket;
import dev.fun.store.backend.dto.OutputBasketDto;

public interface BasketMapper {
	
	/**
	 * Converts {@link Basket} to {@link OutputBasketDto}
	 * @param basket
	 * @return DTO
	 */
	OutputBasketDto fromBasket(Basket basket);
	
}
