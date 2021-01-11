package dev.fun.store.backend.dto;

import java.util.List;

import lombok.Data;

@Data
public class SetAuthorityDto {

	private Long userId;
	private List<Long> authorityId; // Set ?
	
}
