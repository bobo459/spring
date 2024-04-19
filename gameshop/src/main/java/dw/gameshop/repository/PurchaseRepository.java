package dw.gameshop.repository;

import dw.gameshop.model.Purchase;
import dw.gameshop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    //JPA method 명명법에 의거하여 올바른 작명을 해야 함!!!   (모르면 구글에 " jpa 메소드 규칙 " 이라고 검색하시오.)
    //스펙에 명시된 명명법을 제대로 따르기만 하면 JPA가 스펙의 규칙대로 구동 됨.
    List<Purchase> findByUser(User user);  //아무것도 안넣어주면 가장 처음 보이는 걸 넣고 종료한다. 중의!!

}
