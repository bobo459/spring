package dw.wholesale_company.service;

import dw.wholesale_company.model.Department;
import dw.wholesale_company.model.Order;
import dw.wholesale_company.model.Product;
import dw.wholesale_company.repository.ProductRepository;
import org.aspectj.weaver.loadtime.definition.LightXMLParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.print.attribute.standard.OrientationRequested;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProductAll() {
        return productRepository.findAll();
    }


    //2. 제품의 재고가 50개 미만인 제품 정보 얻기
/*    public List<Product> getProductListByInventory(){
        List<Product> productList = productRepository.findAll();
        List<Product> productList1 = new ArrayList<>();
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getInventory()<50){
                productList1.add(productList.get(i));
            }
        }return productList1;
    }*/
    public List<Product> getProductByInventoryUnder(int num) {
        List<Product> productList = productRepository.findAll();
        return productList.stream().filter(p -> p.getInventory() < num)  //filter 바구니에 담는다
                .collect(Collectors.toList());
    }

    //1. 제품 중에 제품명에 '주스'가 들어간 제품에 대한 모든 정보를 검색하시오.
    //      +제품명을 매개변수로 받아서 처리하는 것도 구현해보기
/*    public List<Product> getProductJuce(String name) {
        List<Product> products = productRepository.findAll();
        return products.stream().filter(a -> a.getProductName().contains(name))
                .collect(Collectors.toList());
    }*/
    //선생님 답
    public List<Product> getProductByProductName(String productName) {
        List<Product> productsList = productRepository.findAll();
        return productsList.stream().filter(a -> a.getProductName().contains(productName))  //contains 사용하는게 중요포인트!
                .collect(Collectors.toList());
    }


    //2. 제품 단가가 5,000원 이상 10,000원 이하인 제품에는 무엇이 있는지 검색하시오.
    //       +lowLimit 와 highLimit 으로 매수변수 처리하는 것도 구현해보세요~
/*    public List<Product> getProductunitPrice( int low ){
        List<Product> productList1 = productRepository.findAll();
        return productList1.stream().filter(b->b.getUnitPrice().)
                .collect(Collectors.toList());
    }*/
    //선생님 답
    public List<Product> getProductByPriceRange(int lowPrice, int highPrice) {
        List<Product> productList = productRepository.findAll();
        return productList.stream().filter(product ->
                        product.getUnitPrice() >= lowPrice && product.getUnitPrice() <= highPrice)
                .collect(Collectors.toList());
    }

    //    4.제품 제품번호가 1,2,4,7,11,20인 제품의 모든 정보를 보이시오. (배열로 여러개의 제품을 보이게하고싶다.)
//    포스트맨에서 배열형태로 요청함
//    본문에 [1,2,4,7,11,20] 형태로 요청 (for문사용)
    public List<Product> getProductByIdWithList(@RequestBody List<Long> idList) {
        List<Product> productList = productRepository.findAll();
        List<Product> newProducts = new ArrayList<>();
   /*     for (int i = 0; i < productList.size(); i++) {
            for (int j = 0; j < idList.size(); j++) {
                if (productList.get(i).getProductId() == idList.get(j)) {
                    newProducts.add(productList.get(i));
                }
            }
        }return newProducts;*/
        return productList.stream().filter(product -> idList.contains(product.getProductId())) //contains:자체가 참거짓을 나타낸다
                .collect(Collectors.toList());
    }

    //5. 제품 재고금액이 높은 상위 10개 제품(shot가 편하겠죠?)
    //(재고금액 = 단가(unitPrice) * 재고수량(imventory))
    public List<Product> getProductByLanKing(int unitPrice, int inventory){
        List<Product>  productList = productRepository.findAll();
        return productList.stream().filter(
                product -> product.getUnitPrice() =unitPrice

    }
}
