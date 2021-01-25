package dev.fun.store.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.fun.store.backend.dao.BasketRepository;
import dev.fun.store.backend.dao.ProductRepository;
import dev.fun.store.backend.dao.UserRepository;
import dev.fun.store.backend.domain.Basket;
import dev.fun.store.backend.domain.Product;
import dev.fun.store.backend.domain.User;
import dev.fun.store.backend.dto.InputBasketDto;
import dev.fun.store.backend.dto.OutputBasketDto;
import dev.fun.store.backend.mapper.BasketMapper;
import lombok.Data;

@Service
@Data
public class BasketServiceImpl implements BasketService {
	
	@Autowired
	private BasketRepository basketRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private BasketMapper basketMapper;

	@Override
	@Transactional
	public OutputBasketDto createBasket(InputBasketDto dto) {
		User user = userRepository.getOne(dto.getUserId());
		
		List<Product> productList = getProductsByIDs(dto.getProductId());
		
		Basket basket = new Basket(user);
		basket.setProducts(productList);
		
		return basketMapper.fromBasket(basketRepository.save(basket));
	}

	@Override
	public void deleteBasket(Long id) {
		basketRepository.deleteById(id);
	}

	@Override
	@Transactional
	public OutputBasketDto getBasketByUserId(Long userId) {
		User user = userRepository.getOne(userId);
		return basketMapper.fromBasket(user.getBasket());
	}

	@Override
	public OutputBasketDto getBasket(Long id) {
		return basketMapper.fromBasket(basketRepository.findById(id).orElse(null));
	}

	@Override
	@Transactional
	public OutputBasketDto addProducts(InputBasketDto dto) {
		Basket basket = basketRepository.getOne(dto.getBasketId());
		List<Product> newProducts = getProductsByIDs(dto.getProductId());
		List<Product> productList = new ArrayList<>(basket.getProducts());
		productList.addAll(newProducts);
		basket.setProducts(productList);
		return basketMapper.fromBasket(basketRepository.save(basket));
	}

	@Override
	@Transactional
	public OutputBasketDto removeProducts(InputBasketDto dto) {
		Basket basket = basketRepository.getOne(dto.getBasketId());
		List<Product> removedProducts = getProductsByIDs(dto.getProductId());
		List<Product> productList = new ArrayList<>(basket.getProducts());
		for (Product p : removedProducts) {
			productList.remove(p);
		}
		basket.setProducts(productList);
		return basketMapper.fromBasket(basketRepository.save(basket));
	}
	
	/**
	 * Returns {@code List} of product references using the {@code getOne(id)} method
	 * @param ids {@code List} of product IDs
	 * @return {@code List} of product references
	 */
	private List<Product> getProductsByIDs(List<Long> ids) {
		return ids.stream()
							.map(productRepository::getOne)
							.collect(Collectors.toList());
	}

}
