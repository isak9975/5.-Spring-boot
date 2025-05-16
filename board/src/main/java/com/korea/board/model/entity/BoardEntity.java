package com.korea.board.model.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.Version;

import com.korea.board.model.dto.BoardDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "board")
public class BoardEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String title;
	private String author;
	
	@CreationTimestamp
	private String writingTime;
	private String content;
	
	public BoardDTO toDto() {
		return BoardDTO.builder()
				.id(this.id)
				.title(this.title)
				.author(this.author)
				.writingTime(this.writingTime)
				.content(this.content)
				.build();
	}

}
