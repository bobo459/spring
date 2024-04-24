package dw.wholesale_company.controller;

import dw.wholesale_company.model.Department;
import dw.wholesale_company.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

import java.util.List;
//@RequestMapping  사용하면 자연스럽게 ("/departments") 뒤에 붙는데 <프리픽스>
@RestController
public class DepartmentController {
    DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }
    @GetMapping("/departments")  //레스티이피아이의 통일성이 중요하다. ("/departments")부분을 잘지어야한다.
    public ResponseEntity<List<Department>> getDepartmentAll() {  //ResponseEntity사용을 잊지 마세요.
        return new ResponseEntity<>(departmentService.getDepartmentAll(),
                HttpStatus.OK);
    }


}
