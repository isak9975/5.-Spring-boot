package com.korea.member.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.korea.member.dto.MemberDTO;
import com.korea.member.model.MemberEntity;
import com.korea.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {

	@Autowired
	private final MemberRepository repository;
	
		//전체 회원 조회
		public List<MemberDTO> findAll(){
			
			List<MemberDTO> result = repository.findAll().stream().map(t ->{
				return t.toDto();
			}).collect(Collectors.toList());
			
			return result;
		}
		
		//이메일로 특정 회원 조회
		public List<MemberDTO> findByEmail(String email){
			
			if(email.equals(" ")||email==null||repository.findByEmail(email).isEmpty()) {
				throw new RuntimeException("이메일을 찾을 수 없습니다.");
			}
			
			List<MemberDTO> result = new ArrayList<MemberDTO>();
			
			MemberDTO findDto = repository.findByEmail(email).get().toDto();
			
			result.add(findDto);
			
			return result;
		}
		
		
		//회원 추가
		public List<MemberDTO> create(MemberDTO dto){
			
			validate(dto);
			
			repository.save(dto.toEntity());
			
			return findAll(); 
		}
		
		//이메일로 비밀번호 변경
		public List<MemberDTO> update(@PathVariable String email,@RequestBody MemberDTO dto){
			
			validate(dto);
			
			if(email.equals(" ")||email==null||repository.findByEmail(email).isEmpty()) {
				throw new RuntimeException("이메일을 찾을 수 없습니다.");
			}
			
			MemberEntity memberEntity = findByEmail(email).get(0).toEntity();
			
			memberEntity.setPassword(dto.getPassword());
			 
			repository.save(memberEntity);
			
			return findAll();
		}
		
		//회원아이디로 삭제
		public List<MemberDTO> delete(int id){
		
			repository.deleteById(id);
			
			return findAll();
		}
		
		 public void validate(MemberDTO dto) {
			 if(dto==null) {
				 throw new RuntimeException("DTO is Empty");
			 }
		 }
		
		
		
}
