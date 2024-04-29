package dw.jdbcproject.repository;

import dw.jdbcproject.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
@Primary  //이 구현체가 진짜라고 알려주는것.
public class JdbcTemplatMebmerRepository implements MemberRepository{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Member save(Member member) {
/*        jdbcTemplate.update("insert into members(name) values(?)",
                member.getName());     //get할때 많이 쓰고 post할때는 잘 사용안함.
        return member;*/
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(  //리절트 셋을 가져오기 위해 조금 복잡해 진것이다.
                    "insert into members(name) values(?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, member.getName());
            return ps;
        }, keyHolder);
        member.setId(keyHolder.getKey().longValue());
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return jdbcTemplate
                .query("select * from members id = ?"
                , memberRowMapper(), id)
                .stream().findAny();
    }

    @Override
    public Optional<Member> findByName(String name) {
        return jdbcTemplate
                .query("select * from members name = ?"
                        , memberRowMapper(), name)
                .stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return jdbcTemplate.query("select *from members",
                memberRowMapper());
    }

    private RowMapper<Member> memberRowMapper(){  //행을 맵핑시켜준다. 함수의 형태로 전달하는것.
        return (rs,rowNum) -> {
            Member member = new Member();
            member.setId(rs.getLong(1));
            member.setName(rs.getString(2));
            return member;
        };
    }
}
