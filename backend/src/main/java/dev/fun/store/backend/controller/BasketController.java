package dev.fun.store.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import dev.fun.store.backend.dto.InputBasketDto;
import dev.fun.store.backend.dto.OutputBasketDto;
import dev.fun.store.backend.service.BasketService;
import dev.fun.store.backend.service.BasketServiceImpl;

@RestController
@RequestMapping(path = "/baskets", produces = MediaType.APPLICATION_JSON_VALUE)
public class BasketController {
	
	private final BasketService basketService;
	
	@Autowired	
	public BasketController(BasketServiceImpl basketService) {
		this.basketService = basketService;
	}

	@PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
	public OutputBasketDto createBasket(@RequestBody InputBasketDto dto) {
		return basketService.createBasket(dto);
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteBasket(@PathVariable(name = "id") Long id) {
		basketService.deleteBasket(id);
	}
	
	@GetMapping("/user/{id}")
	public OutputBasketDto getBasketByUserId(@PathVariable(name = "id") Long userId) {
		return basketService.getBasketByUserId(userId);
	}
	
	@GetMapping("/id/{id}")
	public OutputBasketDto getBasket(@PathVariable(name = "id") Long id) {
		return basketService.getBasket(id);
	}
	
	@PutMapping(path = "/add-products", consumes = MediaType.APPLICATION_JSON_VALUE)
	public OutputBasketDto addProducts(@RequestBody InputBasketDto dto) {
		return basketService.addProducts(dto);
	}
	
	@PutMapping(path = "/remove-products", consumes = MediaType.APPLICATION_JSON_VALUE)
	public OutputBasketDto removeProducts(@RequestBody InputBasketDto dto) {
		return basketService.removeProducts(dto);
	}
	
}
