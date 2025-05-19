package com.korea.address.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.korea.address.model.dto.ResponseDTO;
import com.korea.address.model.dto.UserDTO;
import com.korea.address.service.AddressService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/address")
public class AddressController {
	
	@Autowired
	private final AddressService service;
	
	//c
	@PostMapping
	public ResponseEntity<?> create(@RequestBody UserDTO dto){
		List<UserDTO> result = service.create(dto);
		ResponseDTO<UserDTO> response = ResponseDTO.<UserDTO>builder().data(result).build();
		return ResponseEntity.ok(response);
	}
	
	//r
	@GetMapping
	public ResponseEntity<?> findAll(){
		List<UserDTO> result = service.findAll();
		ResponseDTO<UserDTO> response = ResponseDTO.<UserDTO>builder().data(result).build();
		return ResponseEntity.ok(response);
	}
	//u
	//d

}
