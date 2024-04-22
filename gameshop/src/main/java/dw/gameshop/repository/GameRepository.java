package dw.gameshop.repository;

import dw.gameshop.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface GameRepository extends JpaRepository<Game, Long> {  //테이블마다 repository가 있어야한다. 한마디로 엔티티 있을때마다 하나씩 있어야한다.
                                                                     //프라이머리 키의 느낌..?
   // List<Game> findByGenre(String genre)      =>예시                //직접 코드를 여기에 적을때가있는데...

    //Repository 에서 JPQL 사용법 : @Query 어노테이션을 사용함
    @Query("select g1 from Game g1 where g1.price = (select max(g2.price) from Game g2)") //Game부분은 엔티티문구로 해줘야한다.
    public Game getGameWithMaxPrice();

    @Query("select g1 from Game g1 order by g1.price desc")
    public List<Game> getGameWithMaxPriceTop3();

}
