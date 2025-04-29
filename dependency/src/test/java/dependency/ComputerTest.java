package dependency;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;

import lombok.extern.slf4j.Slf4j;


@SpringBootApplication
@SpringBootTest
@Slf4j //롬복에 있는 콘솔을 쓸 수 있는 어노테이션
public class ComputerTest {
	
	//스프링이 메모리에 올려놓은 객체를 변수에 주입
	//@Autowired 
	Coding coding;
	
	//생성자 주입
	@Autowired
	public ComputerTest(Coding coding) {
		this.coding = coding;
	}
	
	
	@Test
	public void ComputerTest() {
		//Coding coding = new Coding();
		//로그에 들어갈 수 있는 내용은 문자열밖에 안된다
		log.info("{결과} : "+ coding.getComputer().getRam());
		
	}
		
}
