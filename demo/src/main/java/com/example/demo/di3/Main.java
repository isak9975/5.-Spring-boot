package com.example.demo.di3;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.google.common.reflect.ClassPath;
import com.google.common.reflect.ClassPath.ClassInfo;

//컴포넌트 스캐닝
//클래스 앞에 @Component 어노테이션을 붙이고
//패키지에서 컴포넌트 어노테이션이 붙어잇는 클래스를 찾아서 
//객체로 만들어서 맵에 저장하는 기법

@Component class Car{};
@Component class SportCar extends Car{};
@Component class Truck extends Car{};
@Component class Engine{};

class AppContext{
	Map map;
	
	public AppContext() {
		map = new HashMap();
		doComponentScan();
	}
	
	private void doComponentScan() {
		try {
			
			//1. 패키지내의 클래스 목록을 가져온다.
			//2. 반복문으로 클래스를 하나씩 읽어와서 @Component가 붙어있는지 확인
			//3. @Component가 붙어있으면 객체를 생성해서 map에 저장
			
			//ClassLoader
			//JVM내부에서 클래스와 리소스(설정 파일, 이미지등)를 로딩하는 역활을 하는 객체.
			
			//AppContext.class.getClassLoader();
			//AppContext 클래스를 로딩한 ClassLoader객체를 반환
			ClassLoader classLoader = AppContext.class.getClassLoader();
			
			
			//ClassPath는 구아바 라이브러리에서 제공하는 클래스로,
			//클래스 경로 상의 모든 클래스를 탐색하고 사용할 수 있게 도와준다.
			ClassPath classPath = ClassPathBeanDefinitionScanner.from(classLoader);
			
			//지정한 패키지내의 최상위 클래스를 가져온다
			Set<ClassPathInfo> set = classPath.getTopLevelClasses("com.example.demo.di3");
			
			
			for(ClassPath.ClassInfo classInfo : set) {
				//classInfo 객체를 실제 Class로 변환을 한다.
				Class clazz = classInfo.load();
				
				//해당 클래스에 @Component 어노테이션이 있는지 확인한다.
				//@Component는 스프링에서 자주 사용되는 어노테이션으로,
				//빈으로 등록하려는 클래스에 부여한다.
				Component component = (Component)clazz.getAnnotation(Component.class);
				
				//@Component 어노테이션이 null이 아니면
				//즉, 해당 클래스가 어노테이션으로 지정되어 있다면
				//아래의 로직을 실행해라
				if(component != null) {
					//클래스의 이름의 첫글자를 소문자로 변환하여 id로 사용할 것이다.
					//변환하는 이유는 스프링에서 빈을 생성할 때, 기본적으로 클래스 이름의 첫글자를
					//소문자로 사용하기 때문이다.			
					String id = StringUtils.uncapitalize(classInfo.getSimpleName());
						
					//newInstance() : 기본 생성자를 호출하여 객체를 생성한다.
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
		
		Car car = (Car)ac.getBean("car");
		System.out.println(car);
		
		Engine engine = (Engine)ac.getBean("Engine");
		System.out.println(engine);
		
		//실제로는 @ComponentScan 어노테이션으로 모든 과정을 퉁친다
		//내부에서는 위와 같은 원리로 돌아가고 있다.
		
	}
}
