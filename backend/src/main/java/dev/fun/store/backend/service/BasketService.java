package dev.fun.store.backend.service;

import dev.fun.store.backend.dto.InputBasketDto;
import dev.fun.store.backend.dto.OutputBasketDto;

public interface BasketService {

	/**
	 * Creates a new basket and saves it in the repository
	 * @param dto {@Link InputBasketDto}
	 * @return {@link OutputBasketDto}
	 */
	OutputBasketDto createBasket(InputBasketDto dto);
	
	/**
	 * Returns basked by user ID
	 * @param userId {@code Long} user ID
	 * @return {@link OutputBasketDto}
	 */
	OutputBasketDto getBasketByUserId(Long userId);
	
	/**
	 * Returns basket by it's ID
	 * @param id {@code Long} basket ID
	 * @return {@link OutputBasketDto}
	 */
	OutputBasketDto getBasket(Long id);
	
	/**
	 * Delete the basket by it's id
	 * @param id {@code Long} basket id
	 */
	void deleteBasket(Long id);
	
}
