package com.example.sbintern3.mapper;

import com.example.sbintern3.dao.Order;
import com.example.sbintern3.dto.OrderDto;
import org.mapstruct.Mapper;


@Mapper
public interface OrderMapper {
    Order sourceToDestination(OrderDto dto);
//    EmployeeDto destinationToSource(Employee employee);
}
