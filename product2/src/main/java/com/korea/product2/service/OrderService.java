package com.korea.product2.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.korea.product2.model.dto.OrderDTO;
import com.korea.product2.model.entity.OrderEntity;
import com.korea.product2.model.entity.ProductEntity;
import com.korea.product2.repository.OrderRepository;
import com.korea.product2.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
	private final OrderRepository repository;
	
	private final ProductRepository productRepository;
	
	//주문내역 조회하기
	public List<OrderDTO> orderFind(){
		List<Object[]> response = repository.findAllOrderTotalPrices();
		
		OrderDTO dto = new OrderDTO();
		
		List<OrderDTO> result = dto.toDto(response);
		
		return result;
	}
	
	
	//주문하기 기능 만들기
	public List<OrderDTO> orderCreate(OrderDTO dto){
		//주문하기 기능 만들기
		//1. 상품의 존재 여부를 확인
		if(dto==null) {
			throw new RuntimeException("dto is empty");
		}
		
		if(productRepository.findById(dto.getProductId()).isEmpty()) {
			 throw new RuntimeException("ProductId is null");
		 }
		
		//시키려는 상품 객체 찾기
		ProductEntity findEntity = productRepository.findById(dto.getProductId()).get(); 
		
		 
		//2. 재고확인( 재고 < 주문개수 ) 예외 발생시킨다. throw newRuntimeException(:"재고부족")
		if(findEntity.getStock() < dto.getProductCount()) {
			throw new RuntimeException("재고가 부족합니다..."); 
		}
		
		//3. 주문하기 (주문내역을 저장)
		repository.save(OrderEntity.builder()
										.product(findEntity) //ProductEntity.builder().id(dto.getProductId()).build()
										.productCount(dto.getProductCount())
											.build());
		
		//4. 재고 감소시키기
		findEntity.setStock(findEntity.getStock() - dto.getProductCount());
		
		//5. 수정된 재고로 다시 데이터 베이스에 저장하기.
		productRepository.save(findEntity);
		
		//6. 전체 내용 반환하기
		return orderFind();
		
				
	}

}//end class
