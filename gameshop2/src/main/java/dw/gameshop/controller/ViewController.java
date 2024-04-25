package dw.gameshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {
    @GetMapping("/login")
    public String login() {
        return "login";
    }//뒤에 .html이 생략되어있다.

    @GetMapping("/articles")
    public String article() {
        return "article";
    }
}
