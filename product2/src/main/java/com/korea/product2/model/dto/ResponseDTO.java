package com.korea.product2.model.dto;

import java.util.List;

import org.springframework.boot.autoconfigure.batch.BatchDataSource;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ResponseDTO<T> {
	private String error;
	private List<T> data;
}
