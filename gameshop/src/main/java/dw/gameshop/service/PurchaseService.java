package dw.gameshop.service;

import dw.gameshop.exception.ResourceNotFoundException;
import dw.gameshop.model.Purchase;
import dw.gameshop.model.User;
import dw.gameshop.repository.PurchaseRepository;
import dw.gameshop.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional  //@Transactional 선택할때 jakarta 로 선택한다.
public class PurchaseService {
    @Autowired
    PurchaseRepository purchaseRepository;
    @Autowired
    UserRepository userRepository;

    public Purchase savePurchase(Purchase purchase){
        //구매확인 바로 직전, 현재시간을 저장함
        purchase.setPurchaseTime(LocalDateTime.now());
        return purchaseRepository.save(purchase);

    }

    public List<Purchase> getAllPurchases(){
        return purchaseRepository.findAll();
    }
    public List<Purchase> getPurchaseListByUser(String userId){  //몇개 샀는지 유저네임별로 작성
        //유저아이디로 유저객체 찾기
        Optional<User> userOptional = userRepository.findByUserId(userId);
        if (userOptional.isEmpty()){
            throw new ResourceNotFoundException("User", "ID", userId);
        }
        return purchaseRepository.findByUser(userOptional.get());  //ID아니고 필드 중에 하나이다. Repository에 만들어 줘야 사용가능
    }

    //유저 이름으로 구매한 게임 찾기
    public List<Purchase> getPurchaseListByUserName(String userName){
        Optional<User> userOptional = userRepository.findByUserName(userName);  //findByUserName 내가 임의로 만든거라 없어서 userRepository에서 만들어줘야함
        if (userOptional.isEmpty()){
            throw new ResourceNotFoundException("User", "Name", userName);
        }
        return purchaseRepository.findByUser(userOptional.get()); //findByUser 유저객체로만 찾을수 있다.() 들어가는 건 반드시 객체여야한다.
    }



}
