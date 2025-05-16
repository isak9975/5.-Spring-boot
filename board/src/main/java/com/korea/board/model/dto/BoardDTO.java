package com.korea.board.model.dto;

import com.korea.board.model.entity.BoardEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BoardDTO {
	private Long id;
	private String title;
	private String author;
	private String writingTime;
	private String content;
	
	public BoardEntity toEntity () {
		return BoardEntity
				.builder()
				.id(this.id)
				.title(this.title)
				.author(this.author)
				.writingTime(this.writingTime)
				.content(this.content)
				.build();
	}
}
