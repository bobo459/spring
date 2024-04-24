package dw.wholesale_company.controller;

import dw.wholesale_company.model.Customer;
import dw.wholesale_company.model.Mileage;
import dw.wholesale_company.model.Order;
import dw.wholesale_company.service.OrderService;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
public class OrderController {
    OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getOrderAll() {
        return new ResponseEntity<>(orderService.getOrderAll(),
                HttpStatus.OK);
    }
    @GetMapping("/orders/date/after/{date}")
    public ResponseEntity<List<Order>> getOrderByDateAfter(@PathVariable LocalDate date) {
        return new ResponseEntity<>(orderService.getOrderByDateAfter(date),
                HttpStatus.OK);
    }

    //3.2020년 4월 9일에 주문한 고객의 모든 정보를 보이시오.
    //    +날짜를 매개변수로 처리
    @GetMapping("/orders/orderdate/{orderDate}")
    public ResponseEntity<List<Customer>> getCustomerByOrderDate(@PathVariable LocalDate orderDate) {
        return new ResponseEntity<>(orderService.getCustomerByOrderDate(orderDate),
                HttpStatus.OK);
    }


}
