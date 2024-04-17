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

    @PostMapping("/api/Employee")
    public Employee saveEmployee(@RequestBody Employee employee){
        //Service Code 가 들어가야함
        return employeeService.saveEmployee(employee);
    }
}
