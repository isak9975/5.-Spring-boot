package com.korea.swagger.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.korea.swagger.dto.PostDTO;

@Service
public class PostService {

	//게시글을 저장하는 db라고 가정
	private final Map<Long, PostDTO> postMap = new HashMap<Long, PostDTO>();
	private Long nextId = 1L;
	
	
	public PostService() {
		savePost(new PostDTO(null, "첫번째 게시글"));
		savePost(new PostDTO(null, "두번째 게시글"));
	}
	
	public List<PostDTO> getAllPosts(){
		return new ArrayList<>(postMap.values());
	}
	
	public PostDTO getPostById(Long id) {
		return new PostDTO(id,"게시글"+id);
	}
	
	//게시글 작성하는 로직
	public PostDTO savePost(PostDTO postDto) {
		postDto.setId(nextId++);
		postMap.put(postDto.getId(),postDto);
		return postDto;
	}
	
	public void deletePostById(Long id) {
		postMap.remove(id);
	}
}
