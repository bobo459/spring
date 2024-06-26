package dw.firsapp.controller;

import dw.firsapp.model.Employee;
import dw.firsapp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {
    // 의존성주입
    @Autowired
    EmployeeService employeeService;

    // 매개변수를 사용한 생성자
    @Autowired  // 생성자 주입(권장하는 방법!!!@Autowired를 생략해도 의존성주입 됨)
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/api/employee") //controller의 역활, 유저가 어떤 주소로 요청해야 불러주나?
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee){  // 객체를 받아서 보내줄수 있게 해줌. -다음으로 서비스로 간다
        //Service Code 가 들어가야함
        return new ResponseEntity<>(employeeService.saveEmployee(employee),
                HttpStatus.OK);
    }
    @GetMapping("api/employee")  //컨트롤러는 간단하게 짜고 서비스에 집중을 많이 하렴
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return new ResponseEntity<>(employeeService.getAllEmployees(),
                HttpStatus.OK);
    }

    @GetMapping("api/employee/{id}")
    public ResponseEntity<Employee> getEmployeeBYId(@PathVariable long id){
        return new ResponseEntity<>( employeeService.getEmployeeBYId(id),
                HttpStatus.OK);
    }

    @PutMapping("api/employee/{id}")  //put : 보낼때, id는PathVariable 에, 수정할 정보는 body에 실어 줘야한다.
    public ResponseEntity<Employee> updateEmployeeById (@PathVariable long id,
                                        @RequestBody Employee employee){
        return new ResponseEntity<>( employeeService.updateEmployeeById(id, employee),
                HttpStatus.OK);
    }

    @DeleteMapping ("api/employee/{id}")
    public ResponseEntity<Employee> deleteEmployyeById (@PathVariable long id){
        return new ResponseEntity<>( employeeService.deleteEmployyeById(id),
                HttpStatus.OK);
    }


}
