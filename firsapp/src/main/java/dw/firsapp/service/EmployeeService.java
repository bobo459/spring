package dw.firsapp.service;

import dw.firsapp.model.Employee;
import dw.firsapp.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    // 의존성주입
    @Autowired
    EmployeeRepository employeeRepository;

    public Employee saveEmployee(Employee employee){ //employee => controller에서 던져준 데이터를 가지고 있다.
        //repository code 가 있어야 함.
        employeeRepository.save(employee);  // 여기서의 save 는 인서트의 역활을 한다.
        return employee;
    }
}
