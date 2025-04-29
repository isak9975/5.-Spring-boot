package com.example.demo.di4;

import java.awt.event.MouseWheelEvent;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.google.common.reflect.ClassPath;
import com.google.common.reflect.ClassPath.ClassInfo;


@Component class Car{

	@Autowired Engine engine;
	@Autowired Wheel wheel;
	@Override
	public String toString() {
		return "Car [engine=" + engine +", wheel=" + wheel +"]";
	}
};
@Component class SportCar extends Car{};
@Component class Truck extends Car{};
@Component class Engine{};
@Component class Wheel{};

class AppContext{

	Map map;
	
	public AppContext() {
		map = new HashMap();
		doComponentScan();
		doAutowired();
	}
	
	//map에 저장된 객체의 객체변수중 @Autowired가 붙어있으면
	//타입에 맞는 객체를 찾아서 연결한다.
	private void doAutowired() {
		try {
			//맵에 들어있는 객체를 하나씩 꺼내서
			for(Object object : map.values()) {
				//getClass()로 클래스 정보를 얻어오고
					//getDeclaredFields()로 해당 클래스에 있는 필드의 정보들을 배열로 반환
				for(Field fld : object.getClass().getDeclaredFields()) {
					//필드에 Autowired 어노테이션이 붙어있는지 확인하고.
					if(fld.getAnnotation(Autowired.class)!=null) {
						//그 필드에 맞는 객체가 있으면 세팅을 해라
//						fld.set(object, getBean(fld.getType()));
					}
				}
				
				
			}
			
		} catch (Exception e) {

		}
		
	}
	
	
	private void doComponentScan() {
		try {
			ClassLoader classLoader = AppContext.class.getClassLoader();
			
			
			ClassPath classPath = ClassPath.from(classLoader);
			

			Set<ClassPath.ClassInfo> set = classPath.getTopLevelClasses("com.example.demo.di4");
			
			
			for(ClassPath.ClassInfo classInfo : set) {
				Class clazz = classInfo.load();
				
				Component component = (Component)clazz.getAnnotation(Component.class);
				
				if(component != null) {			
					String id = StringUtils.uncapitalize(classInfo.getSimpleName());
						
					map.put(id,clazz.newInstance());	
				}					
			}			
		} catch (Exception e) {

		}
	}
	Object getBean(String key) {
		return map.get(key);
	}
}



public class Main {
	public static void main(String[] args) {
		AppContext ac = new AppContext();
		
		//car객체에 필드로 engine과 wheel을 갖는다.
		Car car = (Car)ac.getBean("car");
		
		Engine engine = (Engine)ac.getBean("engine");
		Wheel wheel = (Wheel)ac.getBean("wheel");
		
		//원래 자바에서는 필드에 직접 객체를 넣어줘야한다.(의존성 주입)
		car.engine = engine;
		car.wheel = wheel;
		
		System.out.println(car);
		
				
	}
}
