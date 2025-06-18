package com.korea.todo.model;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class UserEntity {
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid",strategy = "uuid")
	private String id;
	
	private String username;
	private String authProvider;
	private String password;
	
	
	public UserEntity(UserDTO dto) {
		username = dto.getUsername();
		authProvider = dto.getAuthProvider();
		password = dto.getPassword();
		id = dto.getId();
	}

}
