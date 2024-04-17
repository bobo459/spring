package dw.firsapp.controller;

import dw.firsapp.model.Employee;
import dw.firsapp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {
    // 의존성주입
    @Autowired
    EmployeeService employeeService;

    @PostMapping("/api/Employee") //controller의 역활, 유저가 어떤 주소로 요청해야 불러주나?
    public Employee saveEmployee(@RequestBody Employee employee){  // 객체를 받아서 보내줄수 있게 해줌. -다음으로 서비스로 간다
        //Service Code 가 들어가야함
        return employeeService.saveEmployee(employee);
    }
}
