package dw.gameshop.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
//롬복 4개 - 안좋은점. 다양한 환경의 테스트를 하기에는 어울리지 않는다.
@NoArgsConstructor  //기본생성자를 만든다.
@AllArgsConstructor // 전체 다들어있는 값
@Setter
@Getter
@Entity

@Table(name = "purchase")
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //마리아를 사용할때 사용하는 문구. 양식 외우기
    private long id;

    @ManyToOne   //@ManyToOne :  구매를 한 것. 유저의객체가  들어있음 =>다대일 관계를 표현
    @JoinColumn(name = "game_id") //한글가능 => 데이터베이스 컬럼명 sql에서만 사용한다.(자바코드에서는 사용할 일이 없다.)
    private Game game;  //한글쓰지말구  - 객체를 던저줘야 객체로 받음 (자바에서만 사용하는 이름이다)

    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;//@ManyToOne :  다대일 관계로 만들었다.

    @Column(name = "purchase_time")
    private LocalDateTime purchaseTime;

    //1. 롬복을 잊지마세요
    //2.
}
