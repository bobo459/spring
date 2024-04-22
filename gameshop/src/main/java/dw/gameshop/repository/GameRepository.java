package dw.gameshop.repository;

import dw.gameshop.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;


public interface GameRepository extends JpaRepository<Game, Long> {  //테이블마다 repository가 있어야한다. 한마디로 엔티티 있을때마다 하나씩 있어야한다.
                                                                     //프라이머리 키의 느낌..?
   // List<Game> findByGenre(String genre)      =>예시                //직접 코드를 여기에 적을때가있는데...
}
