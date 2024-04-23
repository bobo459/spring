package dw.gameshop.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name = "point",nullable = false)
    private int point;
    @Column(name = "reviewText",length = 65535) //lenght 길이에 따라 문자의 데이터타입이 바뀐다.
    private String reviewText;
    @Column(name = "createdAt",updatable = false) //insertable = false,updatable = false => sql로 안받아 주겠다는 의미.조작불가
    private LocalDateTime createdAt; //생성날짜를 저장할때 변수이름을 createdAt을 많이 사용한다.
                                     //생성과 수정하는 데이터는 다르다. 삭제도 삭제 됬다는 기록이 남아야한다.
}
