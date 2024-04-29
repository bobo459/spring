package dw.jdbcproject.service;

import dw.jdbcproject.model.Member;
import dw.jdbcproject.repository.JdbcMemberRepository;
import dw.jdbcproject.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service   //서비스를 수정하면 안된다.
public class MemberService {
  //  @Autowired
  //  JdbcMemberRepository jdbcMemberRepository; //문제는 안되지만 스프링에서는 사용안하는 방식이다. 1.구현체를 쓰는 방법 (강한 결합)
    @Autowired
    MemberRepository memberRepository; //약한 결합 2.인터페이스를 주입받는 방법, 의존성이 약할수록 스프링답게 짤수 있다.
    public Member saveMember(Member member){
        //레포지토리 save 만들던 자리
      return memberRepository.save(member);
    }
    public List<Member> findAll(){
      return memberRepository.findAll();
    }
}
