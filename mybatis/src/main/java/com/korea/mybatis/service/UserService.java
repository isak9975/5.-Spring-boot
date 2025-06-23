package com.korea.mybatis.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.korea.mybatis.domain.User;
import com.korea.mybatis.mappers.UserMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserMapper mapper;
	
	public List<User> getAllUsers(){
		return mapper.findAll();
	}
	
	public void createUser(User user) {
		mapper.insert(user);
	}
}
