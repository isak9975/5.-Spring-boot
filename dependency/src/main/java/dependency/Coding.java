package dependency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

//코딩은 컴퓨터가 있어야 할 수 있다.
//코딩클래스는 컴퓨터에 의존성이 필요하다.
@Getter@Setter@Component
//@RequiredArgsConstructor
public class Coding {

	
	private Computer computer; //코딩을 하기 위해서는 컴퓨터가 필요하다
	
	//객체를 메모리에 올리면서 생성자는 호출이된다.
	//이때 필요한 의존성을 매개변수에 스프링이 주입을 해준다.
	public Coding(Computer computer) {
		this.computer = computer;
	}
		//컴포넌트가 되어있으니까 스프링이 객체를 올리는데 이때 생성자가 자동 호출된다.
	
	//setter주입
	@Autowired
	public void setComputer(Computer computer) {
		this.computer = computer;
	}
	
	
}
