package com.example.sbintern3.preload;

import com.example.sbintern3.dao.Order;
import com.example.sbintern3.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;


@Component
public class PreLoad implements ApplicationRunner {

    OrderRepository orderRepository;

    @Autowired
    public PreLoad(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception{
        Order order = new Order("Comp", " Dan", 1000);
        Order order1 = new Order("Monitor", "Dan", 1000);
        Order order2 = new Order("Keyboard", "Dan", 1000);
        Order order3 = new Order("Table", "Dan",1000);
        Order order4 = new Order("Mouse", "Dan", 1000);
        Order order5 = new Order("Chair", "Dan", 1000);
        orderRepository.saveAll(Arrays.asList(order,order1,order2,order3,order4,order5));
    }

}
