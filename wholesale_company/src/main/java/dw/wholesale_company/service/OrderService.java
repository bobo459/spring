package dw.wholesale_company.service;

import dw.wholesale_company.model.Order;
import dw.wholesale_company.model.Product;
import dw.wholesale_company.repository.OrderRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    OrderRepository orderRepository;
    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getOrderAll() {
        return orderRepository.findAll();
    }
    
//1. 주문일이 2021년 5월 1일 이후인 주문 정보 얻기
    public List<Order> getOrderListByOrderDate(){
        List<Order> orderList = orderRepository.findAll();
        List<Order> orderList1 = new ArrayList<>();
        for (int i = 0; i < ; i++) {
            
        }
    }
}
