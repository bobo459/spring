package dw.firsapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller //잘 사용안할것이다.
public class HelloPageController {
    @GetMapping("/hello")
    public String hello(){return "redirect:/hello.html";} //바로 static폴더에 있는 hello.html에서 호출한다.


}
