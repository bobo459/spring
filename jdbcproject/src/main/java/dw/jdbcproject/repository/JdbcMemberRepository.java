package dw.jdbcproject.repository;

import dw.jdbcproject.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//구현체, jpa를 사용안하면 구현체를 직접 만들어줘야한다.
@Repository
public class JdbcMemberRepository implements MemberRepository{
    @Autowired
    DataSource dataSource;  //인터페이스
    @Override
    public Member save(Member member) {
        String sql = "insert into members(name) values(?)";  //? 매개변수
        //sql 패키지내의 클래스를 정의
        Connection conn = null; //초기화
        PreparedStatement pstmt = null;  //sql을 그냥은 못보내서pstmt에 쌓서 보내주는 역활
        ResultSet rs = null;
        try {
            conn = DataSourceUtils.getConnection(dataSource);
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, member.getName());
            pstmt.executeUpdate(); //데이터베이스로 내용보내기
            rs = pstmt.getGeneratedKeys();
            if (rs.next()){
                member.setId(rs.getLong(1));  //하나의 로우를 하나씩 읽어오는것
            }else {
                throw new SQLException("ID 조회 실패");
            }
            return member;
        }catch(Exception e) {
            //예외처리
            throw new IllegalStateException(e);
        }finally{
            //연결종료
            close(conn,pstmt,rs);
        }
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Member> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public List<Member> findAll() {
        String sql = "select * form members";  //? 매개변수
        //sql 패키지내의 클래스를 정의
        Connection conn = null; //초기화
        PreparedStatement pstmt = null;  //sql을 그냥은 못보내서pstmt에 쌓서 보내주는 역활
        ResultSet rs = null;
        try {
            conn = DataSourceUtils.getConnection(dataSource);
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            List<Member> members = new ArrayList<>();
            while (rs.next()) {
                Member member = new Member();
                member.setId(rs.getLong(1));
                member.setName(rs.getString(2));
                members.add(member);
            }
            return members;
        }catch(Exception e) {
            //예외처리
            throw new IllegalStateException(e);
        }finally{
            //연결종료
            close(conn,pstmt,rs);
        }
    }
    private void close (Connection conn, PreparedStatement pstmt, ResultSet rs){
        try {
            if (rs!=null){
                rs.close();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }try {
            if (pstmt != null){
                pstmt.close();
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (conn != null){
                conn.close();
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
