package com.korea.todo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.korea.todo.model.UserEntity;
import com.korea.todo.persistence.UserRepository;
import com.korea.todo.security.TokenProvider;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {
	
	//repository 생성자 주입하기
	@Autowired
	private UserRepository repository;

	//유저의 추가
	//회원가입 화면에서 넘어오는 정보
	public UserEntity create(UserEntity entity) {
			//주어진 UserEntity가 null 이거나 또는 username 이 null 인 경우 예외를 발생시킨다.
			if(entity == null || entity.getUsername() == null) {
				log.warn("Invalid arguments {} ", entity==null?entity:entity.getUsername());
				throw new RuntimeException("Invalid arguments");
			}
			
			//entity 에서 username 을 가져온다.
			final String username = entity.getUsername();
			
			//주어진 username이 이미 존재하는 경우, 경로 로그를 남기고 예외를 던진다.
			if(repository.existsByUsername(username)) {
				log.warn("Username is Aready exists {}",username);
				throw new RuntimeException("Username is Aready exists {}");
			}
		
		//username 이 중복되지 않았다면, eneity 객체를 데이커베이스에 추가
		return repository.save(entity);
	}
	
	
	//주어진 username 과 password 를 이요해서 entity 를 조회한다.
	public UserEntity getByCredential(String username,String password) {
		return repository.findByUsernameAndPassword(username, password);
	}
	
	//유저의 삭제
	
	//유저의 수정

}
