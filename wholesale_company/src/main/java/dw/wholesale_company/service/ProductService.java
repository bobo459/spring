package dw.wholesale_company.service;

import dw.wholesale_company.model.Department;
import dw.wholesale_company.model.Order;
import dw.wholesale_company.model.Product;
import dw.wholesale_company.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public List<Product> getProductAll(){
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
        return productList.stream().filter(p->p.getInventory() < num)  //filter 바구니에 담는다
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
    public List<Product> getProductByPriceRange( int lowPrice, int highPrice ){
        List<Product> productList = productRepository.findAll();
        return productList.stream().filter(product->product.getUnitPrice().)
                .collect(Collectors.toList());
    }

}
