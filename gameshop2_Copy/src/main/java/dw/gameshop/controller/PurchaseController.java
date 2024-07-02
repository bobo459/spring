package dw.gameshop.controller;

import dw.gameshop.model.Purchase;
import dw.gameshop.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PurchaseController {
    @Autowired
    PurchaseService purchaseService;

    @PostMapping("/products/purchase")
    public Purchase savePurchase(@RequestBody Purchase purchase) {
        return purchaseService.savePurchase(purchase);
    }

    @PostMapping("/products/purchaselist")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")  //지금은 ADMIN,USER 2경우 다 허용해준다. @PreAuthorize("hasAnyRole('ADMIN')")  권한을 가진 ADMIN인 사람만 가능하다
    public List<Purchase> savePurchase(@RequestBody List<Purchase> purchaseList) {
        return purchaseService.savePurchaseList(purchaseList);
    }

    @GetMapping("/products/purchase")
    public List<Purchase> getAllPurchases() {
        return purchaseService.getAllPurchases();
    }

    @GetMapping("/products/purchase/id/{userId}")
    public List<Purchase> getPurchaseListByUser(@PathVariable String userId) {
        return purchaseService.getPurchaseListByUser(userId);
    }

    @GetMapping("/products/purchase/name/{userName}")
    public ResponseEntity<List<Purchase>> getPurchaseListByUserName(
            @PathVariable String userName) {
        return new ResponseEntity<>(purchaseService.getPurchaseListByUserName(userName),
                HttpStatus.OK);
    }

}








