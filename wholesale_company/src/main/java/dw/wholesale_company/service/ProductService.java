package dw.wholesale_company.service;

import dw.wholesale_company.model.Product;
import dw.wholesale_company.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public List<Product> getProductListByInventory(){
        List<Product> productList = productRepository.findAll();
        List<Product> productList1 = new ArrayList<>();
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getInventory()<50){
                productList1.add(productList.get(i));
            }
        }return productList1;
    }

}
