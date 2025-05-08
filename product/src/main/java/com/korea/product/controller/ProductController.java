package com.korea.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.korea.product.model.ProductDTO;
import com.korea.product.service.ProductService;

import lombok.extern.java.Log;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	//상품추가
	@PostMapping
	public ResponseEntity<?> addProduct(@RequestBody ProductDTO dto){
		List<ProductDTO> response = productService.addProduct(dto);
		return ResponseEntity.ok(response);
	}
	
	//많은상품추가
	@PostMapping("/s")
	public ResponseEntity<?> addProducts(@RequestBody List<ProductDTO> dtolist){
		List<ProductDTO> response = productService.addProducts(dtolist);
		return ResponseEntity.ok(response);
	}
	
	//모든상품조회(최소금액이 있으면 최소금액 이상인 제품만 조회)
	@GetMapping
	public ResponseEntity<?> findAllProduct(){
		List<ProductDTO> response = productService.findAllProduct();
		return ResponseEntity.ok(response);
	}
	
	//모든상품조회(최소금액이 있으면 최소금액 이상인 제품만 조회)
	@GetMapping("/{price}")
	public ResponseEntity<?> findProduct(@PathVariable("price") int price){
		List<ProductDTO> response = productService.findProduct(price);
		return ResponseEntity.ok(response);
	}
	
	//상품수정(id를 가지고 이름, 설명, 가격 수정)
	@PutMapping
	public ResponseEntity<?> updateProduct(@RequestBody ProductDTO dto){
		List<ProductDTO> response = productService.updateProduct(dto);
		return ResponseEntity.ok(response);
	}
	
	//삭제(id를 가지고 상품 삭제)
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable("id") int id){
				
		List<ProductDTO> response = productService.deleteProduct(id);
		
//		boolean isDeleted = productService.deleteProduct(id);
		
//		//삭제가 정상 처리되면 본문 없이 204 No Content 응답
//		if(isDeleted) {
//			return ResponseEntity.noContent().build();
//		}
//		//삭제 실패시 404 Not Found 응답
//		return ResponseEntity.notFound().build();
		
		
		return ResponseEntity.ok(response);
		
	}

}
