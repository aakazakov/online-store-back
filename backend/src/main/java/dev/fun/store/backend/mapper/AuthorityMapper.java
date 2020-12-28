package dev.fun.store.backend.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.factory.Mappers;

import dev.fun.store.backend.domain.authority.Authority;
import dev.fun.store.backend.dto.AuthorityDto;

public interface AuthorityMapper {

	AuthorityMapper MAPPER = Mappers.getMapper(AuthorityMapper.class);
	
	Authority toAuthority(AuthorityDto dto);
	
	@InheritInverseConfiguration
	AuthorityDto fromAuthority(Authority authority);
	
	List<Authority> toAuthorityList(List<AuthorityDto> dtoList);
	
	List<AuthorityDto> fromAuthorityList(List<Authority> authorityList);
	
}
