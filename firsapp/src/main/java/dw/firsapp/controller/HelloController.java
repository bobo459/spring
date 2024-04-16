package dw.firsapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/sayhello") //url (주소) 이구나~~ GetMapping: 누구와 누굴 연결해주었구나~~
    public String hello(){
        return  "Hello World!!";
    }  //누군가가 이걸 호출해 줬구나~~
    
}
