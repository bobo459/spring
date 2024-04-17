package dw.firsapp.controller;

import dw.firsapp.model.Student;
import dw.firsapp.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {
    @Autowired
    StudentService studentService;  //빌리는 방법 끝~! Bean을 빌려주는 관계.정적요소처럼 만들어진것.어플리케이션콘테스트 같은 역활

    //주소에 직접 내용을 보이게 해서 보안에 안좋음
    @GetMapping("/Student")   //JSON의 기본형태 -이종간의 객체를 주고받는 행위
    public Student getStudent() {  //객체하나 보내주기
        return new Student("Tom", "Smith");
    }
    @GetMapping("/students") //객체여러개 보내주기 , 본문안에 넣어주는 행위, 보안에 좋음
    public List<Student> getStudents() {
        List<Student> students = new ArrayList<>();
        students.add(new Student("Tom","Smith"));
        students.add(new Student("John","Long"));
        students.add(new Student("Steve","White"));
        students.add(new Student("Leon","Red"));
        students.add(new Student("Mike","Tyson"));
        return students;
    }
    @GetMapping("/student/{firstName}/{lastName}")  // /{firstName}/{lastName} 이 친구들을 호출하겠다. 중괄호 있는 이유는, 컨트롤러입장에서 설명하기 위한것
                                                    //"/student/firstName/lastName" 이렇게 사용하면 주소를 쓴건가보다 하고 착가하므로 {변수} 인것을 알려주기 위한것이다
    public Student studentPathvariable(@PathVariable String firstName,  //@GetMapping("/student/{firstName}/{lastName}") 톰캣이 여기서 불러오겠다는 의미.
                                       @PathVariable String lastName) {  //Path(주소) Variable(변수) 방법
        return new Student(firstName, lastName);
    }
    @GetMapping("/student/query")  // query {}가 없으니 주소이다. query로 끝나는 모든 용청사항이 있으면 @RequestParam 으로 보내라는 의미.
    //localhost:8080/student/query?firstName=Tom&lastName=Smith  : ? 뒤의 영역은 변수명으로 RequestParam의 영역이다
    public Student studentRequestParam(
            @RequestParam String firstName,
            @RequestParam String lastName){
        return new Student(firstName,lastName);
    }

    @PostMapping("student/post")        //post 는 메세지 본문에 적혀있다. 그 본문이 Body 이다. 객체는 body에 받는다 사이즈가 좀 있어서.
    public Student studentPost(@RequestBody Student student){    // body의 내용을 가져와서 student타입으로 캐스팅한다.
        System.out.println(student.getFirstName() + " " + student.getLastName());
        return new Student(student.getFirstName(),student.getLastName());
    }
    @GetMapping("/student/score/{firstName}/{lastName}")
    public int getStudentScore(@PathVariable String firstName,
                               @PathVariable String lastName){
        Student student = new Student(firstName,lastName);
        //StudentService는 Bean이므로 인스턴화하지 않는다.
        //StudentService studentService = new StudentService();
        return studentService.getStudentScore(student);
    }

}
