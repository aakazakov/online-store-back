package dev.fun.store.backend.service;

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
		
		List<Product> productList = dto.getProductId()
				.stream()
				.map(productRepository::getOne)
				.collect(Collectors.toList());
		
		Basket basket = new Basket(user);
		basket.setProducts(productList);
		
		return basketMapper.fromBasket(basketRepository.save(basket));
	}

	@Override
	public void deleteBasket(Long id) {
		basketRepository.deleteById(id);
	}

}
