package dev.fun.store.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.fun.store.backend.dto.UserDto;
import dev.fun.store.backend.service.UserService;
import dev.fun.store.backend.service.UserServiceImpl;

@RestController
@RequestMapping("/users")
public class UserController {

	private UserService userService;
	
	@Autowired
	public UserController(UserServiceImpl userService) {
		this.userService = userService;
	}
	
	@GetMapping(path = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<UserDto> getAllUsers() {
		return userService.getAll();
	}
	
	@GetMapping(path = "/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public UserDto getUser(@PathVariable(name = "id") Long id) {
		return userService.getOne(id);
	}
	
	@PostMapping(path = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
	public UserDto addUser(UserDto dto) {
		return userService.save(dto);
	}
	
	@PutMapping(path = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
	public UserDto updateUser(UserDto dto) {
		return userService.update(dto);
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteUser(@PathVariable(name = "id") Long id) {
		userService.delete(id);
	}
	
}
