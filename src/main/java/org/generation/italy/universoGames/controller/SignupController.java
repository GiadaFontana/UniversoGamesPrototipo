package org.generation.italy.universoGames.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.generation.italy.universoGames.auth.AuthService;

@RestController
@RequestMapping("/signup")
public class SignupController {
	@Autowired
	private AuthService authService;

	@PostMapping
	public String signup(@RequestParam String email, 
							@RequestParam String username, 
							@RequestParam String password,
							@RequestParam String ruolo
							) {
		authService.signup(email, username, password, ruolo);
		return "OK";
	}
}
