package dev.fun.store.backend.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/permit", produces = MediaType.APPLICATION_JSON_VALUE)
public class PermissionController {

	@GetMapping("/is-auth")
	public Map<String, Boolean> isAuth(Principal principal) {
		Map<String, Boolean> response = new HashMap<>();
		boolean value = principal != null;
		response.put("auth", value);
		return response;
	}
	
	@GetMapping("/csrf")
	public CsrfToken csrf(CsrfToken token) {
		return token;
	}
	
}
