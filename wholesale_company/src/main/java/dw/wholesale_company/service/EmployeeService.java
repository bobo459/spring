package dw.wholesale_company.service;

import dw.wholesale_company.model.Employee;
import dw.wholesale_company.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class EmployeeService {
    EmployeeRepository employeeRepository;
    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getEmployeeAll(){
        return employeeRepository.findAll();
    }

    //3. 사원의 직위가 '사원'인 사람들 중에서 가장 최근에 입사한 사원의 정보 ,선생님답
    //포지션이 사원이 사람을 뽑고 그 후 사원들만 정렬한다음에 뽑으면 된다.
    public Employee getEmployeeByHireLatest() {
        return employeeRepository.findAll()
                .stream().filter(e->e.getPosition().equals("사원"))
                .sorted(Comparator.comparing(Employee::getHireDate).reversed())  //sorted : 리턴을 할수있음. 기본으로 오름차순이다 ,sort : 보이드형 리턴을 할수 없다.
                .findFirst().get();  //findFirst 처음만 나오게 하고  .get리스트라 get을 붙여준것이다.근데 여러명이 동시 입사할수 있을수 있으니 그때는 다를수있다.
    }
}
