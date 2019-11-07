package com.example.sbintern3.controller;


import com.example.sbintern3.dao.Order;
import com.example.sbintern3.dto.OrderDto;
import com.example.sbintern3.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
public class OrderController {
    OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders/all")
    public  List<Order> all() {
        return orderService.takeAll();
    }

    @GetMapping("/test")
    String test() {
        return "test";
    }
    @PostMapping("/orders")
    public ResponseEntity<String> createOder(@RequestBody OrderDto newOrder){

    return ResponseEntity.ok(orderService.createNewOrder(newOrder));
    }
}

