package dev.fun.store.backend.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import dev.fun.store.backend.domain.authority.Authority;
import dev.fun.store.backend.dto.AuthorityDto;

@Mapper
public interface AuthorityMapper {

	AuthorityMapper MAPPER = Mappers.getMapper(AuthorityMapper.class);

	AuthorityDto fromAuthority(Authority authority);
	
	List<AuthorityDto> fromAuthorityList(List<Authority> authorityList);
	
}
