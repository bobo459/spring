package dw.jdbcproject.controller;

import dw.jdbcproject.model.Member;
import dw.jdbcproject.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MemberController {
    @Autowired
    MemberService memberService;

    @PostMapping("/member/new")
    public ResponseEntity<Member> saveMember(@RequestBody Member member){
        // 방법1
        return ResponseEntity.ok(memberService.saveMember(member));
        // 방법2
        //return new ResponseEntity<>(memberService.saveMember(member),HttpStatus.OK);
    }
    @GetMapping("/member")
    public ResponseEntity<List<Member>> findAll(){
        return ResponseEntity.ok(memberService.findAll());
    }
//    @GetMapping("/member/id")
//    public ResponseEntity
}
