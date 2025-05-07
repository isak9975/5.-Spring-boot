package com.korea.user.model;

import com.korea.user.dto.UserDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.bytebuddy.asm.Advice.This;

@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="users")
public class UserEntity {
	
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	//JPA에서 기본 키를 자동으로 생성하는 방법을 정의하는 어노테이션
	//H@와 같은 내장 데이터베이스를 사용하는 경우, 기본적으로 숫자 값이
	//증가하는 방식으로 ID가 설정된다.
	private int id;
	private String name;
	private String email;
	
	public UserDTO toDto() {
		return UserDTO.builder().id(this.id).name(this.name).email(this.email).build();
	}
	
}
