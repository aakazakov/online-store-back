package dev.fun.store.backend.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import dev.fun.store.backend.domain.Basket;
import dev.fun.store.backend.dto.BasketDetailDto;
import dev.fun.store.backend.dto.OutputBasketDto;
import dev.fun.store.backend.dto.ProductDto;

public class BasketMapperImpl implements BasketMapper {
	
	@Override
	public OutputBasketDto fromBasket(Basket basket) {
		OutputBasketDto outputBasketDto = new OutputBasketDto();
		List<BasketDetailDto> basketDetailDtoList = new ArrayList<>();
		Integer totalAmount = 0;
		Long totalCost = 0L;
		
		List<ProductDto> productDtoList = ProductMapper.MAPPER.fromProductList(basket.getProducts());
		Map<ProductDto, Integer> productDtoMap = getProductDtoMap(productDtoList);
		
		Iterator<Map.Entry<ProductDto, Integer>> iterator = productDtoMap.entrySet().iterator();		
		while(iterator.hasNext()) {
			Map.Entry<ProductDto, Integer> entry = iterator.next();
			BasketDetailDto basketDetailDto = fromProductDtoToBasketDetailDto(entry.getKey(), entry.getValue());
			totalAmount += basketDetailDto.getAmount();
			totalCost += basketDetailDto.getTotalCost();
			basketDetailDtoList.add(basketDetailDto);
		}
		
		outputBasketDto.setBasketId(basket.getId());
		outputBasketDto.setDetails(basketDetailDtoList);
		outputBasketDto.setTotalAmount(totalAmount);
		outputBasketDto.setTotalCost(totalCost);
		
		return outputBasketDto;
	}
	
	private Map<ProductDto, Integer> getProductDtoMap(List<ProductDto> list) {
		Map<ProductDto, Integer> productDtoMap = new HashMap<>();
		
		for (ProductDto dto : list) {
			if (productDtoMap.containsKey(dto)) {
				Integer amount = productDtoMap.get(dto);
				++amount;
				productDtoMap.put(dto, amount);
			} else {
				productDtoMap.put(dto, 1);
			}
		}
		
		return productDtoMap;
	}
	
	private BasketDetailDto fromProductDtoToBasketDetailDto(ProductDto productDto, Integer amount) {
		BasketDetailDto basketDetailDto = new BasketDetailDto();
		Long totalCost = productDto.getCost() * amount;
		basketDetailDto.setProductDto(productDto);
		basketDetailDto.setAmount(amount);
		basketDetailDto.setTotalCost(totalCost);
		return basketDetailDto;
	}

}
