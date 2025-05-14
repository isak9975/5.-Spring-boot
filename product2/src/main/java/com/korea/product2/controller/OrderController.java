package com.korea.product2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.korea.product2.model.dto.OrderDTO;
import com.korea.product2.service.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("orders")
public class OrderController {
	
	@Autowired
	private final OrderService service;
	
	//주문조회 기능 만들기
	@GetMapping
	public ResponseEntity<?> orderFind(){
		List<OrderDTO> list = service.orderFind();
		return ResponseEntity.ok(list);
	}
	
	
	//주문하기 기능 만들기
	@PostMapping
	public ResponseEntity<?> orderCreate(@RequestBody OrderDTO dto){
		List<OrderDTO> list = service.orderCreate(dto);
		return ResponseEntity.ok(list);
	}

}
