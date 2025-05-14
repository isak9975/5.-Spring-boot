package com.korea.member.dto;

import com.korea.member.model.MemberEntity;

import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberDTO {
	
	private int id;
	private String name;
	private String email;
	private String password;
	
	public MemberEntity toEntity() {
		return MemberEntity
				.builder()
				.id(this.id)
				.name(this.name)
				.email(this.email)
				.password(this.password)
				.build();
	}

}
