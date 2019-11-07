package com.example.sbintern3.repository;

import com.example.sbintern3.dao.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order,Long > {
}
