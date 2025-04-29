package dependency.qualifier;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j //junit5 부터는 @SpringbootTest 안붙혀도됨.
public class QualifierTest {
	
	//컴퓨터 타입의 객체를 주입받으려고 한다.
	//Laptop도 있고 Desktop도 있다.
	//스프링 입장에서는 개발자가 어떤걸 원하는지 알수 없다.
	@Autowired
	@Qualifier("laptop")
	Computer laptop;
	
	@Autowired
	@Qualifier("desktop")
	Computer desktop;

	@Autowired
	Computer computer;
	
	//선언할때마다 qualifier 붙이기 귀찮으면
	//하나를 선택한 후 @Primary를 붙여주면
	//기본값으로 설정되어서 그것이 나오게 된다.
	
	@Test
	public void computerTest() {
		log.info("모니터 너비: {} ",laptop.getScreenWidth());
		log.info("모니터 너비: {} ",desktop.getScreenWidth());
		log.info("모니터 너비: {} ",computer.getScreenWidth());
	}
	

}
