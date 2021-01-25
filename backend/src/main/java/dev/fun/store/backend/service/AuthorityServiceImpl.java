package dev.fun.store.backend.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.fun.store.backend.dao.AuthorityRepository;
import dev.fun.store.backend.dao.UserRepository;
import dev.fun.store.backend.domain.User;
import dev.fun.store.backend.domain.authority.Authority;
import dev.fun.store.backend.dto.AuthorityDto;
import dev.fun.store.backend.dto.SetAuthorityDto;
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
		return AuthorityMapper.MAPPER.fromAuthority(authorityRepository.findById(id).orElse(null));
	}

	@Override
	public List<AuthorityDto> getAll() {
		return AuthorityMapper.MAPPER.fromAuthorityList(authorityRepository.findAll());
	}

	@Override
	public List<UserDto> getAllUsersByAuthorityId(Long id) {
		return UserMapper.MAPPER.fromUserList(userRepository.findUsersByAuthorityId(id));
	}

	@Override
	public List<AuthorityDto> getAllAuthoritiesByUserId(Long id) {
		return AuthorityMapper.MAPPER.fromAuthorityList(authorityRepository.findAuthoritiesByUserId(id));
	}

	@Override
	@Transactional
	public List<AuthorityDto> setAuthorities(SetAuthorityDto dto) {
		User user = userRepository.getOne(dto.getUserId());
		List<Authority> authorities = authorityRepository.findAllById(dto.getAuthorityId());
		user.setAuthorities(authorities);
		User updatedUser = userRepository.save(user);
		return AuthorityMapper.MAPPER.fromAuthorityList(updatedUser.getAuthorities());
	}

}
