package com.korea.address.model.dto;

import com.korea.address.model.entity.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
	
	private int id;
	private String userId;
	private String userPassword;
	private String userAddress;
	private String userEmail;
	
	public UserEntity toEntity() {
		return UserEntity.builder().id(this.id).userId(this.userId).userPassword(this.userPassword).userAddress(this.userAddress).userEmail(this.userEmail).build();
	}
}
