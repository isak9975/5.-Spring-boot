package com.example.demo.di2;

import java.util.HashMap;
import java.util.Map;

class Car{};
class SportCar extends Car{};
class Truck extends Car{};
class Engine{};

//객체 컨테이너로 객체를 저장
//객체 컨테이너 : 객체들을 젖아하는 공간 
//클래스 안에 Map으로 객체르 ㄹ저장

class AppContext{
	Map map;//객체 저장소
	
	//AppContext 객체를 생성함과 동시에
	//생성자로 인해 map에 값이 자동으로 들어간다.
	public AppContext() {
		//스프링은 객체를 메모리에 미리 올려놓고 시작을 한다.
		map = new HashMap();
		map.put("car",new SportCar());
		map.put("engine", new Engine());
	}
	
	//key를 반환하는 메서드
	Object getBean(String key) {
		return map.get(key); 			
	}
	
}


public class Main1 {
	public static void main(String[] args) {
	
		AppContext ac = new AppContext();
		
		
		//필요할때마다 객체를 호출해서 사용한다.
			//반환값이 Object이므로 형변환을 해준다 
			Car car = (Car)ac.getBean("car");
			//Car객체의 값을 받아올수 있다.
			System.out.println("Car = " + car);
			
			//반환값이 object이므로 형변환으로 해준다.
			Engine engine = (Engine)ac.getBean("engine");
			//Engine객체의 값을 받아 올수 있다.
			System.out.println("engine = "+engine);
		
		
	}
}
