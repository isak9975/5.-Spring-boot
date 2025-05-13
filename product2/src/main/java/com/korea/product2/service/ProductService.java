package com.korea.product2.service;

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
	//d

}
