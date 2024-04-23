package dw.wholesale_company.controller;

import dw.wholesale_company.model.Order;
import dw.wholesale_company.model.OrderDetail;
import dw.wholesale_company.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderDetailController {

    OrderDetailService orderDetailService;

    public OrderDetailController(OrderDetailService orderDetailService) {
        this.orderDetailService = orderDetailService;
    }
    @GetMapping("/orderdetails")
    public ResponseEntity<List<OrderDetail>> getOrderDertailAll() {
        return new ResponseEntity<>(orderDetailService.getOrderDertailAll(),
                HttpStatus.OK);
    }
}
