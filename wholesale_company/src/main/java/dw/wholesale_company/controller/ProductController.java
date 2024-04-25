package dw.wholesale_company.controller;

import dw.wholesale_company.model.Customer;
import dw.wholesale_company.model.Product;
import dw.wholesale_company.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class ProductController {

    ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProductAll() {
        return new ResponseEntity<>(productService.getProductAll(),
                HttpStatus.OK);
    }

    /*    @GetMapping("/product/list")
        public ResponseEntity<List<Product>> getProductListByInventory(){
            return new ResponseEntity<>(productService.getProductListByInventory(),
                    HttpStatus.OK);
        }*/
    @GetMapping("/products/inventory/under/{num}")
    public ResponseEntity<List<Product>> getProductByInventoryUnder(@PathVariable int num) {
        return new ResponseEntity<>(productService.getProductByInventoryUnder(num),
                HttpStatus.OK);
    }

    /*    @GetMapping("/products/name/{name}")
        public ResponseEntity<List<Product>> getProductJuce(@PathVariable String name){
            return new ResponseEntity<>(productService.getProductJuce(name),
                    HttpStatus.OK);
        }*/
    @GetMapping("/products/{productName}")
    public ResponseEntity<List<Product>> getProductByProductName(@PathVariable String productName) {
        return new ResponseEntity<>(productService.getProductByProductName(productName),
                HttpStatus.OK);
    }

    //2. 제품 단가가 5,000원 이상 10,000원 이하인 제품에는 무엇이 있는지 검색하시오.
    //      +lowLimit 와 highLimit 으로 매수변수 처리하는 것도 구현해보세요~
    //@PathVariable 복수일때 불편한 매개변수 , 복수일때는 @RequestParam 이 편리하다. 잘사용은 안함.
    @GetMapping("/products/price")
    public ResponseEntity<List<Product>> getProductByPriceRange(@RequestParam int low,
                                                                @RequestParam int high) {
        return new ResponseEntity<>(productService.getProductByPriceRange(low, high),
                HttpStatus.OK);
    }


    //4.제품 제품번호가 1,2,4,7,11,20인 제품의 모든 정보를 보이시오.
    @GetMapping("/products/idlist")
    public ResponseEntity<List<Product>> getProductByIdWithList(@RequestBody List<Long> idList) {
        return new ResponseEntity<>(productService.getProductByIdWithList(idList),
                HttpStatus.OK);
    }

    //5. 제품 재고금액이 높은 상위 10개 제품(shot가 편하겠죠?)
    //(재고금액 = 단가(unitPrice) * 재고수량(imventory))
    @GetMapping("/products/inventoryprice/{limit}")
    public ResponseEntity<List<Product>> getProductByInventoryPrice(@PathVariable int limit) {
        return new ResponseEntity<>(productService.getProductByInventoryPrice(limit),
                HttpStatus.OK);
    }
}
