package com.korea.user.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.korea.user.dto.ResponseDTO;
import com.korea.user.dto.UserDTO;
import com.korea.user.model.UserEntity;
import com.korea.user.service.UserService;

@RestController
@RequestMapping("users")
public class UserController {

	@Autowired
	private UserService userService;
		
	//유저 추가.
	@PostMapping
	public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO){
		
		UserEntity dataEntity = userDTO.toEntity();
		
		List<UserDTO> dtos = userService.create(dataEntity);
		
//		ResponseDTO<UserDTO> response = ResponseDTO.<UserDTO>builder().data(dtos).build();
		
		return ResponseEntity.ok(dtos); 
	}
	
	//전체 유저 찾기.
	@GetMapping
	public ResponseEntity<?> findAll(@RequestBody UserDTO userDTO){
		List<UserDTO> users = userService.findAll();
		return ResponseEntity.ok(users);
	}
	
	//이메일로 사용자 검색하기
	@GetMapping("/{email}")
	public ResponseEntity<?> findByEmail(@PathVariable(name = "email") String email){
		UserDTO response =  userService.findByEmail(email);
		return ResponseEntity.ok(response);
	}
	
	
	//ID를 통해 이름과 이메일 수정하기
	@PostMapping("/update")
	public ResponseEntity<?> updateUser(@RequestBody UserDTO userDTO){
		List<UserDTO> response = userService.updateUser(userDTO); 
		return ResponseEntity.ok(response);
	}
	
	
		
}
