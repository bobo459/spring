package dw.gameshop.controller;

import dw.gameshop.model.Purchase;
import dw.gameshop.model.User;
import dw.gameshop.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PurchaseController {
    @Autowired
    PurchaseService purchaseService;

    @PostMapping("products/purchase")
    public Purchase savePurchase(@RequestBody Purchase purchase){
        return purchaseService.savePurchase(purchase);
    }
    @GetMapping("products/purchase")
    public List<Purchase> getAllPurchaese() {
        return purchaseService.getAllPurchases();
    }
    @GetMapping("products/purchase/{userId}")
    public List<Purchase> getPurchaseListByUser(@PathVariable String userId){
     return purchaseService.getPurchaseListByUser(userId);
    }
    @GetMapping("/products/purchase/name/{userName}") //("/products/purchase/{userName}")이 상태는 위의 상태와 경로가 다른지 같은지 톰캣이 알수가 없다.
    public ResponseEntity<List<Purchase>> getPurchaseListByUserName(@PathVariable String userName){
        return new ResponseEntity<>(purchaseService.getPurchaseListByUserName(userName),
                HttpStatus.OK);
    }


}
