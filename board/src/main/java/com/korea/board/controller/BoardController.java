package com.korea.board.controller;

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

import com.korea.board.model.dto.BoardDTO;
import com.korea.board.model.dto.ResponseDTO;
import com.korea.board.service.BoardService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/board")
public class BoardController {
	
	@Autowired
	private final BoardService service;
	
	//C 한번에 넣기
	@PostMapping("/all")
	public ResponseEntity<?> createAll(@RequestBody List<BoardDTO> list){
		List<BoardDTO> result = service.createAll(list);
		ResponseDTO response = ResponseDTO.<BoardDTO>builder().data(result).build();
		return ResponseEntity.ok(response);
	}
	
	//C 하나씩 넣기
	@PostMapping
	public ResponseEntity<?> create(@RequestBody BoardDTO dto){
		List<BoardDTO> result = service.create(dto);
		ResponseDTO response = ResponseDTO.<BoardDTO>builder().data(result).build();
		return ResponseEntity.ok(response);
	}
	
	//R
	@GetMapping
	public ResponseEntity<?> findAll(){
		List<BoardDTO> result = service.findAll();
		ResponseDTO response = ResponseDTO.<BoardDTO>builder().data(result).build();
		return ResponseEntity.ok(response);
	}
	
	//R id를 통한 게시글 한 건 조회하기("/{id}")(@PathVariable 사용하기)
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable(name = "id")Long id){
		List<BoardDTO> result = service.findById(id);
		ResponseDTO response = ResponseDTO.<BoardDTO>builder().data(result).build();
		return ResponseEntity.ok(response);
	}
	
	//U
	@PutMapping("/{id}/post")
	public ResponseEntity<?> update(@PathVariable(name = "id")Long id,@RequestBody BoardDTO dto){
		List<BoardDTO> result = service.update(id,dto);
		ResponseDTO response = ResponseDTO.<BoardDTO>builder().data(result).build();
		return ResponseEntity.ok(response);	
	}
	
	//D
	@DeleteMapping("{id}")
	public ResponseEntity<?> delete(@PathVariable(name = "id")Long id){
		boolean result = service.delete(id);
		return ResponseEntity.ok(result);
	}
	

}
