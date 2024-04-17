package dw.firsapp.service;

import dw.firsapp.model.Student;
import org.springframework.stereotype.Service;

@Service  // 있어야 controller와 대화가 가능하다
public class StudentService {
    public int getStudentScore(Student student){
        System.out.println(student.getFirstName() + " " + student.getLastName());
        return 100;
    }
}
