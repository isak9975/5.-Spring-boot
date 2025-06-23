package com.korea.mybatis.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.korea.mybatis.domain.User;

@Mapper
public interface UserMapper {

	@Select("SELECT*FROM 'user'")
	List<User> findAll(); //전체 유저 조회
	
	@Select("SELECT *FROM'user' where #{id}")
	User findByIdUser(Long id); //id를 통한 유저 한건 조회
	
	@Insert("INSERT INTO 'user' (name,email) values(#{name},#{email} ")
	void insert(User user); //유저 추가하기
	void update(User user); // 유저 수정하기
	void delete(Long id); //id를 통한 유저 삭제 하기.
}
