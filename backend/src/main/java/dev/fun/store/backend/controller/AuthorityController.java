package dev.fun.store.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.fun.store.backend.dto.AuthorityDto;
import dev.fun.store.backend.service.AuthorityService;
import dev.fun.store.backend.service.AuthorityServiceImpl;

@RestController
@RequestMapping("/authorities")
public class AuthorityController {
	
	private final AuthorityService authorityService;

	@Autowired
	public AuthorityController(AuthorityServiceImpl authorityService) {
		this.authorityService = authorityService;
	}
	
	@GetMapping(path = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<AuthorityDto> getAllProducts() {
		return authorityService.getAll();
	}
	
	@GetMapping(path = "/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public AuthorityDto getProduct(@PathVariable(name = "id") Long id) {
		return authorityService.getOne(id);
	}
	
}
