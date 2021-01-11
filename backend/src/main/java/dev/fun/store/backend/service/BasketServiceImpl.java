package dev.fun.store.backend.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.fun.store.backend.dao.BasketRepository;
import dev.fun.store.backend.dao.ProductRepository;
import dev.fun.store.backend.dao.UserRepository;
import dev.fun.store.backend.dto.InputBasketDto;
import dev.fun.store.backend.dto.OutputBasketDto;

@Service
public class BasketServiceImpl implements BasketService {
	
	@Autowired
	private BasketRepository basketRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	@Transactional
	public OutputBasketDto createBasket(InputBasketDto dto) {
		return null;
	}

	@Override
	public void deleteBasket(Long id) {
		basketRepository.deleteById(id);
	}

}
