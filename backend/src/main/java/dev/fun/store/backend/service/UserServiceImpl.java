package dev.fun.store.backend.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import dev.fun.store.backend.dao.AuthorityRepository;
import dev.fun.store.backend.dao.UserRepository;
import dev.fun.store.backend.domain.User;
import dev.fun.store.backend.domain.authority.Authority;
import dev.fun.store.backend.dto.UserDto;
import dev.fun.store.backend.mapper.UserMapper;
import dev.fun.store.backend.mapper.UserMapperDecorator;

@Service
public class UserServiceImpl implements UserService {
	
	private final UserRepository userRepository;
	private final AuthorityRepository authorityRepository;

	@Autowired
	public UserServiceImpl(UserRepository userRepository, AuthorityRepository authorityRepository) {
		this.userRepository = userRepository;
		this.authorityRepository = authorityRepository;
	}

	@Override
	public UserDto getOne(Long id) {
		return UserMapper.MAPPER.fromUser(userRepository.findById(id).orElse(null));
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
		Long id = dto.getId();
		String newUsername = dto.getUsername();
		String newPassword = dto.getPassword();
		Boolean newEnabled = dto.getEnabled();
		
		if (id == null)
			return null;
		
		User user = userRepository.getOne(id);
		
		if (newUsername != null)
			user.setUsername(newUsername);
		if (newPassword != null && !newPassword.equals(UserMapperDecorator.STUB))
			user.setPassword(newPassword);
		if (newEnabled == null)
			user.setEnabled(newEnabled);
		
		return UserMapper.MAPPER.fromUser(userRepository.save(user));
	}
	
	@Override
	public UserDto getByUsername(String username) {
		return UserMapper.MAPPER.fromUser(userRepository.findFirstByUsername(username));
	}

	@Override
	public void delete(Long id) {
		userRepository.deleteById(id);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findFirstByUsername(username);
    List<GrantedAuthority> authorities = new ArrayList<>();
    user.getAuthorities().forEach(auth ->
    	authorities.add(new SimpleGrantedAuthority(auth.getTitle()))
    );
    return new org.springframework.security.core.userdetails.User(
        user.getUsername(), user.getPassword(), authorities);
	}

}
