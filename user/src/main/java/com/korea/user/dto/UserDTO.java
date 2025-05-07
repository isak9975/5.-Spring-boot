package com.korea.user.dto;

import com.korea.user.model.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//클라이언트와 주고받을 때(요청,응답) DTO에 담아서 주자
//데이터가 계층간 이동할 때 controller -> service -> repository
//Entity 에 담아서 옮기자

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
	private int id;
	private String name;
	private String email;
	
	public UserEntity toEntity() {
		return UserEntity.builder().id(this.id).name(this.name).email(this.email).build();
	}
}
