package com.korea.product2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.korea.product2.model.dto.ProductDTO;
import com.korea.product2.model.dto.ResponseDTO;
import com.korea.product2.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("product2")
public class ProductController {
	
	@Autowired
	private final ProductService service;
	
	//c
	@PostMapping
	public ResponseDTO<?> create(@RequestBody ProductDTO dto){
		List<ProductDTO> response = service.create(dto);
		return ResponseDTO.<ProductDTO>builder().data(response).build();
	}
	
	//r
	@GetMapping
	public ResponseDTO<?> findAll(){
		List<ProductDTO> response = service.findAll();
		return ResponseDTO.<ProductDTO>builder().data(response).build();
	}
	//u
	//d

}
