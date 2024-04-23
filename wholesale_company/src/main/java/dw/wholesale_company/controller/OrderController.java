package dw.wholesale_company.controller;

import dw.wholesale_company.model.Mileage;
import dw.wholesale_company.model.Order;
import dw.wholesale_company.service.OrderService;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
