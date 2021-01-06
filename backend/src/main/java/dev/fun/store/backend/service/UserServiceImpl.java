package dev.fun.store.backend.service;

import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.fun.store.backend.dao.AuthorityRepository;
import dev.fun.store.backend.dao.UserRepository;
import dev.fun.store.backend.domain.User;
import dev.fun.store.backend.domain.authority.Authority;
import dev.fun.store.backend.dto.UserDto;
import dev.fun.store.backend.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService{
	
	private final UserRepository userRepository;
	private final AuthorityRepository authorityRepository;

	@Autowired
	public UserServiceImpl(UserRepository userRepository, AuthorityRepository authorityRepository) {
		this.userRepository = userRepository;
		this.authorityRepository = authorityRepository;
	}

	@Override
	public UserDto getOne(Long id) {
		return UserMapper.MAPPER.fromUser(userRepository.findById(id).orElse(new User()));
	}

	@Override
	public List<UserDto> getAll() {
		return UserMapper.MAPPER.fromUserList(userRepository.findAll());
	}

	@Override
	@Transactional
	public UserDto saveClient(UserDto dto) {
		User user = UserMapper.MAPPER.toUser(dto);
		user.setEnabled(true);
		user = userRepository.save(user);
		// contains ROLE_CLIENT & ADD_COMMENTS
		List<Authority> authList = authorityRepository.findAllById(Arrays.asList(3L, 4L));
		user.setAuthorities(authList);
		return UserMapper.MAPPER.fromUser(userRepository.save(user));
	}

	@Override
	@Transactional
	public UserDto update(UserDto dto) {
		// TODO
		return null;
	}

	@Override
	public void delete(Long id) {
		userRepository.deleteById(id);
	}

}
