package com.korea.board.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.korea.board.model.dto.BoardDTO;
import com.korea.board.model.entity.BoardEntity;
import com.korea.board.repository.BoardRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {
	
	@Autowired
	private final BoardRepository repository;

	
	//C 한번에 넣기
	@Transactional
	public List<BoardDTO> createAll(List<BoardDTO> list){
		if(list.isEmpty()) {
			throw new RuntimeException("한번에 넣기 실패 : 배열이 비어있음.");
		}
		
		list.stream().map(t -> {
			BoardEntity trans = t.toEntity();
			repository.save(trans);
			return true;
		}).collect(Collectors.toList());
		
		return findAll();
	}
	
	//C 하나씩 넣기
	public List<BoardDTO> create(BoardDTO dto){
		
		vaildate(dto);
		
		BoardEntity entity =repository.save(dto.toEntity());
		
		if(entity==null) {
			throw new RuntimeException("작성 실패 : 받아올 값이 없습니다.");
		}
		
		return findAll(); 
	}
	
	//R
	public List<BoardDTO> findAll(){
		//JAVA 8 이상에서 사용가능(수정가능)
		List<BoardDTO> reulst = repository.findAll().stream().map(t -> {
			return t.toDto();
		}).collect(Collectors.toList());
		
//		JAVA 16 이상에서 사용가능.(수정불가)		
//		List<BoardDTO> reulst = repository.findAll().stream().map(t -> {
//			return t.toDto();
//		}).toList();
		
		return reulst;
	}
	
	//R id 를 통한 게시글 한건 조회하기
	public List<BoardDTO> findById(Long id) {
		
		if(id <= 0) {
			throw new RuntimeException("[개별조회]id가 올바르지 않습니다.");
		}
		
		List<BoardDTO> list = new ArrayList<BoardDTO>() ;
		
		if(repository.findById(id).isPresent()) {	
			list.add(repository.findById(id).get().toDto());
		}
			
		return list; 
	}
	
	//U
	@Transactional
	public List<BoardDTO> update(Long id,BoardDTO dto){
		
		vaildate(dto);
		
		if(!repository.existsById(id)) {
			throw new RuntimeException("없는 id 입니다.");
		}
		
		BoardEntity findEntity = repository.findById(id).get();	

		findEntity.setAuthor(dto.getAuthor());
		findEntity.setContent(dto.getContent());
		findEntity.setTitle(dto.getTitle());
		
		repository.save(findEntity);
		
		return findAll();
	}
	
	//D
	@Transactional
	public boolean delete(Long id){
		
		if(!repository.existsById(id)) {
			throw new RuntimeException("없는 id 입니다.");
		}
		
		if(repository.existsById(id)) {
			
			repository.deleteById(id);
			
		}else {
			return false;
		}
		
		return true; 
	}
	
	
	
	//DTO 검증
	public void vaildate(BoardDTO dto) {
		if(dto==null) {
			throw new RuntimeException("[오류] DTO가 비어있습니다.");
		}
		 
	}
	
	
	
}
