package com.korea.address.model.entity;

import com.korea.address.model.dto.UserDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class UserEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String userId;
	private String userPassword;
	private String userAddress;
	private String userEmail;
	
	public UserDTO toDto() {
		return UserDTO.builder().id(this.id).userId(this.userId).userPassword(this.userPassword).userAddress(this.userAddress).userEmail(this.userEmail).build();
	}

}
