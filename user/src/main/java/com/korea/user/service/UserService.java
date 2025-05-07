package com.korea.user.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.korea.user.dto.UserDTO;
import com.korea.user.model.UserEntity;
import com.korea.user.repository.UserRepository;

import jakarta.persistence.Entity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor //final 이나 @NonNull이 붙은 필드를 생성자의 매개변수로 포함.
public class UserService {
	
	@Autowired
	final UserRepository userRepository;
	
	//전부 찾기
	public List<UserDTO> findAll(){
		
		List<UserDTO> dtos = userRepository.findAll().stream().map(entity -> {
			return entity.toDto();
		}).collect(Collectors.toList());
		
		return dtos;
	}
	
	
	
	
	//유저 생성 메서드
	public List<UserDTO> create(UserEntity userEntity){
		
//		vaildate(userEntity);
		
		userRepository.save(userEntity);
		
		List<UserDTO> dtos = userRepository.findAll().stream().map(entity -> {
			return entity.toDto();
		}).collect(Collectors.toList());
		
		return dtos;
	}
	
	
	//이메일을 사용하여 유저 찾기
	public UserDTO findByEmail(String email) {
		return userRepository.findByEmail(email).toDto(); 
	}
	
	
	//ID를 통해 이름과 이메일 수정하기
	public UserDTO updateUser(UserDTO userDTO) {
		
		UserEntity userEntity = userDTO.toEntity();
		
		//Optional 에 제네릭을 안줘도 작동은 하지만 
		//정확하게 정해진 객체가 없으면 해당 객체의 함수를 사용하지 못하기 때문이(setter,getter)
		//제네릭으로 정해주는게 좋다.
		Optional<UserEntity> findEntity = userRepository.findById(userEntity.getId());
		
		findEntity.ifPresent(entity -> {
			entity.setName(userEntity.getName());
			entity.setEmail(userEntity.getEmail());
			
			//ifPresent 내부에서 세팅 및 저장을 한번에!
			userRepository.save(entity);
		});		
		
		return findEntity.get().toDto();
	}
	
	
	
	
	
	
	
	
	
	//유효성 검사
//	public void vaildate(UserEntity userEntity) {
//		if(userEntity==null) {
//			throw new RuntimeException("값을 읽을 수 없습니다.");
//		}
//		
//		if(userEntity.getId()==null) {
//			throw new RuntimeException("알수 없는 유저");
//		}
//	}
	
}
