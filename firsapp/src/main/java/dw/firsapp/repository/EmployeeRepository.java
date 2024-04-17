package dw.firsapp.repository;

import dw.firsapp.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

//EmployeeRepository 의미는? SQL 메서드들을 여기에 만들고, sql 메서드들을 서비스에서 가져다 씀
public interface EmployeeRepository extends JpaRepository< Employee,Long > {  //Long : 프라이머리의 데이터 키를 입력한 것

}
