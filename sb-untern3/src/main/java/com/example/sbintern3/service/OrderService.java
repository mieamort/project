package com.example.sbintern3.service;


import com.example.sbintern3.dao.Order;
import com.example.sbintern3.dto.OrderDto;
import com.example.sbintern3.mapper.OrderMapper;
import com.example.sbintern3.repository.OrderRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OrderService {
    OrderRepository repository;
    OrderMapper mapper = Mappers.getMapper(OrderMapper.class);


    @Autowired
    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    public List<Order> takeAll() {
        return ((List<Order>) repository.findAll());
    }

    public String createNewOrder(OrderDto dto) {
        Order order = mapper.sourceToDestination(dto);
        repository.save(order);
        String s = "Заказ добавлен, его id - " + order.getId()+ ", a заказал его - "+ order.getClientName();
        return s;

    }
}
