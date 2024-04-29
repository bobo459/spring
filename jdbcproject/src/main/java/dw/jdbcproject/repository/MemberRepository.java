package dw.jdbcproject.repository;

import dw.jdbcproject.model.Member;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

public interface MemberRepository {
    //메서드 선언만 했음.구현체가 없음. 구현체도 직접만들어 줘야한다.
    Member save(Member member);
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
