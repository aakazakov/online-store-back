package dev.fun.store.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import dev.fun.store.backend.dto.AuthorityDto;
import dev.fun.store.backend.dto.SetAuthorityDto;
import dev.fun.store.backend.dto.UserDto;
import dev.fun.store.backend.service.AuthorityService;
import dev.fun.store.backend.service.AuthorityServiceImpl;

@RestController
@RequestMapping(path = "/authorities", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthorityController {
	
	private final AuthorityService authorityService;

	@Autowired
	public AuthorityController(AuthorityServiceImpl authorityService) {
		this.authorityService = authorityService;
	}
	
	@GetMapping("/all")
	public List<AuthorityDto> getAllProducts() {
		return authorityService.getAll();
	}
	
	@GetMapping("/id/{id}")
	public AuthorityDto getProduct(@PathVariable(name = "id") Long id) {
		return authorityService.getOne(id);
	}
	
	@GetMapping("/id/{id}/users")
	public List<UserDto> getUsersByAuthorityId(@PathVariable(name = "id") Long id) {
		return authorityService.getAllUsersByAuthorityId(id);
	}
	
	@GetMapping("/all/user/{id}")
	public List<AuthorityDto> getAllAuthoritiesByUserId(@PathVariable(name = "id") Long id) {
		return authorityService.getAllAuthoritiesByUserId(id);
	}
	
	@PutMapping(path = "/set", consumes = MediaType.APPLICATION_JSON_VALUE)
	public List<AuthorityDto> setAuthorities(@RequestBody SetAuthorityDto dto) {
		return authorityService.setAuthorities(dto);
	}
 	
}
