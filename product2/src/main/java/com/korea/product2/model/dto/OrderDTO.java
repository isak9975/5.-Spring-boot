package com.korea.product2.model.dto;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.aspectj.util.IStructureModel;
import org.springframework.data.jpa.repository.Query;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor

public class OrderDTO {
	
	private int orderId;	//주문번호
	private int productId;	//상품번호
	private String productName;	//상품 이름
	private int productCount;	//주문 개수
	private int productPrice;	//상품 가격
	private int totalPrice;	//결제 금액
	private String orderDate;	//주문 날짜
	
	//Object[]에 들어있는 데이터를 OrderDTO로 변환
	public List<OrderDTO> toDto(List<Object[]> list) {
		//Object -> Integer -> int
		return list.stream().map(result->OrderDTO.builder()
										.orderId((int)result[0])
										.productId((int)result[1])
										.productName((String)result[2])
										.productCount((int)result[3])
										.productPrice((int)result[4])
										.totalPrice((int)result[5])
										.orderDate(LocalDateTime.now().toString())
											.build()).collect(Collectors.toList());
		
		
		
	} 
}
