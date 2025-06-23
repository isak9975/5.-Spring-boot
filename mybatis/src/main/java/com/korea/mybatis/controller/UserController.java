package com.korea.mybatis.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.korea.mybatis.domain.User;
import com.korea.mybatis.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
	
	private final UserService service;
	
	@GetMapping
	public List<User> getAllUsers() {
		
		return service.getAllUsers();
	}
	
	@PostMapping
	public void createUser(@RequestBody User user) {
		service.createUser(user);
	}

}
