package com.korea.product.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.swing.ListModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.korea.product.model.ProductDTO;
import com.korea.product.model.ProductEntity;
import com.korea.product.repository.ProductRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductService {

	@Autowired
	private ProductRepository repository;
	
	//상품추가
		public List<ProductDTO> addProduct(ProductDTO dto){
			ProductEntity productEntity = new ProductEntity(dto);
			repository.save(productEntity);
			
			List<ProductDTO> result = repository.findAll().stream().map(ProductDTO::new).collect(Collectors.toList());
		
			return result;
		}
		
	//많은 상품 추가
		public List<ProductDTO> addProducts(List<ProductDTO> dtolist){
			
			 dtolist.stream().map(dto ->{
				 System.out.println(dto.toEntity());
				 log.debug("엔티티 내부의 값 : {}",dto.toEntity());
				 return repository.save(dto.toEntity());
			 }).collect(Collectors.toList());
			
			
			List<ProductDTO> result = repository.findAll().stream().map(ProductDTO::new).collect(Collectors.toList());
			
			return result;
			
		}
		
		//모든상품조회
			public List<ProductDTO> findAllProduct(){
				
				List<ProductDTO> result = repository.findAll().stream().map(ProductDTO::new).collect(Collectors.toList());
			
				return result;
			}
		
		//모든상품조회(최소금액이 있으면 최소금액 이상인 제품만 조회)
		public List<ProductDTO> findProduct(int price){
			
			List<ProductEntity> allEntities = repository.findAll();
			
			List<ProductDTO> result = allEntities.stream().filter(t -> t.getPrice()>=price).map(ProductDTO::new).collect(Collectors.toList());
			
			return result;
		}
		
		//상품수정(id를 가지고 이름, 설명, 가격 수정)
		public List<ProductDTO> updateProduct(ProductDTO dto){
			Optional<ProductEntity> response = repository.findById(dto.getId());
			
			response.ifPresent(res -> {
				res.setName(dto.getName());
				res.setDescrption(dto.getDescrption());
				res.setPrice(dto.getPrice());
				
				repository.save(res);
			});
			
			return findAllProduct();
		}
		
		//삭제(id를 가지고 상품 삭제)
		public List<ProductDTO> deleteProduct(int id){
			
			repository.deleteById(id);
			
			return  findAllProduct();
		}
	
}
