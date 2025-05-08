package com.korea.product.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class ProductEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	private String descrption;
	private int price;
	
	public ProductEntity(ProductDTO dto) {
		this.id = dto.getId();
		this.name = dto.getName();
		this.descrption = dto.getDescrption();
		this.price = dto.getPrice();		
	}
	
	public ProductDTO toDto() {
		return ProductDTO.builder().id(this.id).name(this.name).descrption(this.descrption).price(this.price).build();
	}

}
