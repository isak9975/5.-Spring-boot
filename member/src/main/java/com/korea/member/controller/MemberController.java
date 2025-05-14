package com.korea.member.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.korea.member.dto.MemberDTO;
import com.korea.member.dto.ResponseDTO;
import com.korea.member.service.MemberService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("members")
public class MemberController {

	@Autowired
	private final MemberService service;
	
	//전체 회원 조회
	@GetMapping
	public ResponseEntity<?> findAll(){
		List<MemberDTO> list = service.findAll();
		ResponseDTO result = ResponseDTO.<MemberDTO>builder().data(list).build();
		return ResponseEntity.ok(result);
	}
	
	@GetMapping("/{email}")
	//이메일로 특정 회원 조회
	public ResponseEntity<?> findByEmail(@PathVariable(name = "email") String email){
		List<MemberDTO> list = service.findByEmail(email);
		ResponseDTO result = ResponseDTO.<MemberDTO>builder().data(list).build();
		return ResponseEntity.ok(result);
	}
	
	
	//회원 추가
	@PostMapping
	public ResponseEntity<?> create(@RequestBody MemberDTO dto){
		List<MemberDTO> list = service.create(dto);
		ResponseDTO result = ResponseDTO.<MemberDTO>builder().data(list).build();
		return ResponseEntity.ok(result);
	}
	
	//이메일로 비밀번호 변경
	@PutMapping("/{email}/password")
	public ResponseEntity<?> update(@PathVariable(name = "email") String email,@RequestBody MemberDTO dto){
		List<MemberDTO> list = service.update(email,dto);
		ResponseDTO result = ResponseDTO.<MemberDTO>builder().data(list).build();
		return ResponseEntity.ok(result);
	}
	
	//회원아이디로 삭제
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable(name = "id") int id){
		List<MemberDTO> list = service.delete(id);
		ResponseDTO result = ResponseDTO.<MemberDTO>builder().data(list).build();
		return ResponseEntity.ok(result);
	}

}
