package dev.fun.store.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import dev.fun.store.backend.dao.AuthorityRepository;
import dev.fun.store.backend.domain.authority.Authority;
import dev.fun.store.backend.dto.AuthorityDto;
import dev.fun.store.backend.mapper.AuthorityMapper;

public class AuthorityServiceImpl implements AuthorityService{

	private final AuthorityRepository authorityRepository;
	
	@Autowired
	public AuthorityServiceImpl(AuthorityRepository authorityRepository) {
		this.authorityRepository = authorityRepository;
	}

	@Override
	public void delete(Long id) {
		authorityRepository.deleteById(id);
	}

	@Override
	public AuthorityDto getOne(Long id) {
		return AuthorityMapper.MAPPER.fromAuthority(authorityRepository.findById(id).orElse(new Authority()));
	}

	@Override
	public List<AuthorityDto> getAll() {
		return AuthorityMapper.MAPPER.fromAuthorityList(authorityRepository.findAll());
	}

	@Override
	public AuthorityDto save(AuthorityDto dto) {
		Authority authority = AuthorityMapper.MAPPER.toAuthority(dto);
		return AuthorityMapper.MAPPER.fromAuthority(authorityRepository.save(authority));
	}

}
