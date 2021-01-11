package dev.fun.store.backend.dto;

import java.util.List;

import lombok.Data;

/**
 * Used to transfer the user ID and a list of the actual user authority IDs
 */
@Data
public class SetAuthorityDto {

	private Long userId;
	private List<Long> authorityId;
	
}
