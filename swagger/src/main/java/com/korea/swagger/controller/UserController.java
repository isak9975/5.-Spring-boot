package com.korea.swagger.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.korea.swagger.dto.UserDTO;
import com.korea.swagger.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/users")
@Tag(name = "User API",description = "사용자 정보 관리 API")
public class UserController {
	
	@Autowired private UserService service;
	
	@GetMapping
	@Operation(summary = "모든 사용자 조희",description = "전체 사용자 목록은 반환합니다.")
	public List<UserDTO> getAllUsers(){
		return service.getAllUsers();
	}
	
	@GetMapping("/{id}")
	@Operation(summary = "사용자 단건 조회", description = "ID로 사용자를 조회합니다.")
	public UserDTO getUserById(@Parameter(description = "사용자 ID",example = "1")
			@PathVariable(name = "id") Long id) {
		return service.getUserById(id);
	}
	

}
