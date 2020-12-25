package dev.fun.store.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.fun.store.backend.dao.UserRepository;
import dev.fun.store.backend.domain.User;

@Service
public class UserServiceImpl implements UserService{
	
	private final UserRepository userRepository;

	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public User getOne(Long id) {
		return userRepository.findById(id).orElse(new User());
	}

	@Override
	public List<User> getAll() {
		return userRepository.findAll();
	}

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public User update(User user) {
		// TODO
		return null;
	}

	@Override
	public void delete(Long id) {
		userRepository.deleteById(id);
	}

}
