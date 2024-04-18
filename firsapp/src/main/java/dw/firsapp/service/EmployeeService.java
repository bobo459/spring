package dw.firsapp.service;

import dw.firsapp.model.Employee;
import dw.firsapp.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    // 의존성주입
    @Autowired
    EmployeeRepository employeeRepository;  // employeeRepository 이 Bean 타입이다

    public Employee saveEmployee(Employee employee){ //employee => controller에서 던져준 데이터를 가지고 있다.
        //repository code 가 있어야 함.
        employeeRepository.save(employee);  // 여기서의 save 는 인서트의 역활을 한다.
        return employee;
    }
    public List<Employee> getAllEmployees(){
       return employeeRepository.findAll(); //  findAll : 묶어서 리스트로 만들어준다.
    }
    public Employee getEmployeeBYId(long id){  //많이 사용되는 구조  , localhost:8080/api/employee/7
        Optional<Employee> employee = employeeRepository.findById(id);  //Optional으로 랩핑한것. 정보와 매칭되는 정보를 찾아주세요. 없으면 null
        if (employee.isEmpty()){
            //예외처리
            return null; // 원래는 안되는데 임시로 예외처리를 null 로 해줌
        }else {
            /*return employee;//하면 안되다.*/
            return employee.get();
        }
    }
    public Employee updateEmployeeById(long id, Employee employee){
        //ID로 해당 데이터 찾기
        Optional<Employee> employee1 = employeeRepository.findById(id);
        if (employee1.isPresent()){
            //데이터(변경하여) 업데이트
            employee1.get().setEmail(employee.getEmail());
            employee1.get().setFirstName(employee.getFirstName());
            employee1.get().setLastName(employee.getLastName());
            //실재로 DB에 저장하기
            employeeRepository.save(employee1.get());

            return employee1.get();
        }else {
            return null;
        }
    }
    public Employee deleteEmployyeById(long id){
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()){
            employeeRepository.deleteById(id);
            return employee.get();
        }else {
            return null;
        }
    }



}
