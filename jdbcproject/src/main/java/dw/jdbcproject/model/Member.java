package dw.jdbcproject.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter   //엔티티를 넣을수 없다. jpa를 설치도 안했기 때문에 사용할수 없다. 그러므로 테이블이 생성되지도 않는다. DBeaver에서 테이블을 직접만들어줘야한다.
public class Member {
    private long id;
    private String name;
}
