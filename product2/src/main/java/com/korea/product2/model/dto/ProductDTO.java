package com.korea.product2.model.dto;

import java.time.LocalDateTime;

import com.korea.product2.model.entity.ProductEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
	
	private int id;
	private String name;
	private int stock;
	private int price;
	private LocalDateTime createTime;
	private LocalDateTime updateTime;
	
	public ProductEntity toEntity () {
		return ProductEntity
				.builder()
					.id(this.id)
					.name(name)
					.stock(this.stock)
					.price(this.price)
					.createTime(this.createTime)
					.updateTime(this.updateTime)
						.build();
	}
}
