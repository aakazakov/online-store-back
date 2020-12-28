package dev.fun.store.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.fun.store.backend.dao.AuthorityRepository;
import dev.fun.store.backend.dao.UserRepository;
import dev.fun.store.backend.domain.authority.Authority;
import dev.fun.store.backend.dto.AuthorityDto;
import dev.fun.store.backend.dto.UserDto;
import dev.fun.store.backend.mapper.AuthorityMapper;
import dev.fun.store.backend.mapper.UserMapper;

@Service
public class AuthorityServiceImpl implements AuthorityService{

	private final AuthorityRepository authorityRepository;
	private final UserRepository userRepository;
	
	@Autowired
	public AuthorityServiceImpl(AuthorityRepository authorityRepository, UserRepository userRepository) {
		this.authorityRepository = authorityRepository;
		this.userRepository = userRepository;
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
	public List<UserDto> getAllUsersByAuthority(Long id) {
		return UserMapper.MAPPER.fromUserList(userRepository.findUsersByAuthorityId(id));
	}

}
