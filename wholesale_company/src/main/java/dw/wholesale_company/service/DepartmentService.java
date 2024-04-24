package dw.wholesale_company.service;

import dw.wholesale_company.model.Department;
import dw.wholesale_company.model.Order;
import dw.wholesale_company.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentService {
    DepartmentRepository departmentRepository;
    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public List<Department> getDepartmentAll(){
        return departmentRepository.findAll();
    }




}
