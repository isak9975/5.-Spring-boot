package com.korea.product2.service;

import java.sql.ResultSet;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.korea.product2.model.dto.ProductDTO;
import com.korea.product2.model.entity.ProductEntity;
import com.korea.product2.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
	
	@Autowired
	private final ProductRepository repository;
	
	//c
	public List<ProductDTO> create(ProductDTO dto){
		
		//넘어온 엔티티가 유요한지 검사
		validate(dto);
		
//		if(dto.getName().equals(" ")||dto.getPrice()==0||dto.getStock()==0) {
//			throw new RuntimeException("do not");
//		}
		
		repository.save(dto.toEntity());
		
		return findAll();
	}
	
	//r
	public List<ProductDTO> findAll() {
		List<ProductEntity> entity = repository.findAll();
		
		List<ProductDTO> result = entity.stream().map(t -> {
			return t.toDto();
		}).collect(Collectors.toList());
		
		return result;
	}
	//u
	public List<ProductDTO> update(ProductDTO dto){
		
		//넘어온 엔티티가 유요한지 검사
		validate(dto);
		
		repository.findById(dto.getId()).ifPresent(t->{
			t.setName(dto.getName());
			t.setPrice(dto.getPrice());
			t.setStock(dto.getStock());
		});
		
		return findAll();
	}
	
	//d
	public List<ProductDTO> delete(ProductDTO dto){
		//넘어온 엔티티가 유요한지 검사
		validate(dto);
		
		repository.findById(dto.getId()).ifPresent(t ->{ 
			repository.deleteById(dto.getId()); 
		});
		return findAll(); 
	}
	
	private void validate(ProductDTO dto) {
		if(dto==null) {
			throw new RuntimeException("Dto cannot be null");
		}
	}
}
