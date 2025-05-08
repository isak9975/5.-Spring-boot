package com.korea.product.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDTO {
	private int id;
	private String name;
	private String descrption;
	private int price;
	
	public ProductDTO(ProductEntity entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.descrption = entity.getDescrption();
		this.price = entity.getPrice();
	}
	
	public ProductEntity toEntity() {
		return ProductEntity.builder().id(this.id).name(this.name).descrption(this.descrption).price(this.price).build();
	}

}
