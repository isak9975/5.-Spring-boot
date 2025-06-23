package com.korea.swagger.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.korea.swagger.dto.PostDTO;
import com.korea.swagger.service.PostService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
@Tag(name = "Post API", description = "게시글 관리 API" )
public class PostController {

	@Autowired private final PostService service;
	
	//조회
	@GetMapping
	@Operation(summary = "게시글 목록 조회", description = "전체 게시글을 조회")
	public List<PostDTO> getAllPosts(){
		return service.getAllPosts();
	}
	
	
	//단건조회
	@GetMapping("/{id}")
	@Operation(summary = "게시글 단건 조회",description = "ID로 게시글을 조회합니다")
	public PostDTO getPostById(
			@Parameter(description = "게시글 ID",example = "1")
			@PathVariable("id") Long id) {
		return service.getPostById(id);
		
	}
	
	//추가
	@PostMapping
	@Operation(summary = "게시글 등록",description = "새로운 게시글을 등록합니다")
	public PostDTO createPost(@Parameter(description = "등록할 게시글 정보",required = true)
			@RequestBody PostDTO dto) {
		return service.savePost(dto);
	}
	
	//삭제
	@Operation(summary = "게시글 삭제",description = "ID로 게시글을 삭제합니다")
	@DeleteMapping("/{id}")
	public void deletePost(@Parameter(description = "삭제할 게시글 ID", example = "1")
							@PathVariable("id") Long id
			) {
		service.deletePostById(id);
	}
}
