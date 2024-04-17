package dw.firsapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication  //@ComponentScan : 컴퍼넌트를 스캔하는 역할. 자기와 같은 레벨의 형제들을 읽어서 사용하게 해준다. 중요!!
public class FirsappApplication { //시작지점.

	public static void main(String[] args) {

		SpringApplication.run(FirsappApplication.class, args);
	}

}
