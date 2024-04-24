package dw.wholesale_company.controller;

import dw.wholesale_company.model.Product;
import dw.wholesale_company.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<List<Product>> getProductByProductName(@PathVariable String productName){
        return new ResponseEntity<>(productService.getProductByProductName(productName),
                HttpStatus.OK);
    }


    //@PathVariable 복수일때 불편한 매개변수 , 복수일때는 @RequestParam 이 편리하다. 잘사용은 안함.
}
