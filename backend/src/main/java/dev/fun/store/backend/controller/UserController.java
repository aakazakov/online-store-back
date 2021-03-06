package dev.fun.store.backend.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import dev.fun.store.backend.dto.UserDto;
import dev.fun.store.backend.service.UserService;
import dev.fun.store.backend.service.UserServiceImpl;

@RestController
@RequestMapping(path = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

	private UserService userService;
	
	@Autowired
	public UserController(UserServiceImpl userService) {
		this.userService = userService;
	}
	
	@GetMapping("/current")
	public UserDto getCurrentUser(Principal principal) {
		if (principal == null) {
			return null;
		}
		return userService.getByUsername(principal.getName());
	}
	
	@GetMapping("/all")
	public List<UserDto> getAllUsers() {
		return userService.getAll();
	}
	
	@GetMapping("/id/{id}")
	public UserDto getUser(@PathVariable(name = "id") Long id) {
		return userService.getOne(id);
	}
	
	@PostMapping(path = "/add-client", consumes = MediaType.APPLICATION_JSON_VALUE)
	public UserDto addClient(@RequestBody UserDto dto) {
		return userService.saveClient(dto);
	}
	
	@PutMapping(path = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
	public UserDto updateUser(@RequestBody UserDto dto) {
		return userService.update(dto);
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteUser(@PathVariable(name = "id") Long id) {
		userService.delete(id);
	}
	
}
