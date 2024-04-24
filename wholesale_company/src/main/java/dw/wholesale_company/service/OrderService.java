package dw.wholesale_company.service;

import dw.wholesale_company.model.Order;
import dw.wholesale_company.model.Product;
import dw.wholesale_company.repository.OrderRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
/*
//1. 주문일이 2021년 5월 1일 이후인 주문 정보 얻기
    public List<Order> getOrderListByOrderDate(){
        List<Order> orderList = orderRepository.findAll();
        List<Order> orderList1 = new ArrayList<>();
        for (int i = 0; i < ; i++) {
            
        }
    }*/
    public List<Order> getOrderByDateAfter(LocalDate date){
        List<Order> orders = orderRepository.findAll();
        return orders.stream().filter(a->a.getOrderDate().compareTo(date)>0) // filter:순서가 있어야 사용가능하다.
                .collect(Collectors.toList());  //stream : 객체타입 뭉쳐있는 자료중 하나씩 뽑아준다.
    }  //람다식 :함수형프로그래밍,절차형프로그래밍이라 한다.. 순서대로 차례로 절차적으로 작업을 한다. 리스트로 시작했으면 리스트로 끝내야한다.
}
