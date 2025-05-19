package com.korea.address.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.korea.address.model.dto.UserDTO;
import com.korea.address.model.entity.UserEntity;
import com.korea.address.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AddressService {
	
	@Autowired
	private final UserRepository repository;

	//c
	public List<UserDTO> create(UserDTO dto){
		
		validate(dto);
		repository.save(dto.toEntity());
		return findAll();
	}
	
	//r
	public List<UserDTO> findAll(){
		List<UserDTO> result = repository.findAll().stream().map(UserEntity::toDto).collect(Collectors.toList());	
		return result;
	}
	//u
	//d
	
	//validate
	public void validate(UserDTO dto) {
		if(dto==null) {
			throw new RuntimeException("dto is null");
		}
	}
}
