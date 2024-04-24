package dw.wholesale_company.repository;

import dw.wholesale_company.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,String> {  //자바가 데이터베이스와 소통하기 위해 가지고 있는 JDBC 이 있다.
}//Jpa는 인터페이스와 같지만 JDBC와 같지는 않다. 빈메소드 같다. ~하라고 알려주는 기능이다.JDBC는 내가 사용안하지만 JDBC를 Jpa가 사용한다.