package com.example.sbintern3.serviceTest;

import com.example.sbintern3.dao.Order;
import com.example.sbintern3.dto.OrderDto;
import com.example.sbintern3.mapper.OrderMapper;
import com.example.sbintern3.repository.OrderRepository;
import com.example.sbintern3.service.OrderService;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

@SpringBootTest
public class OrderServiceTest {

    @Mock
    OrderRepository repository;

    @Mock
    OrderMapper mapper;

    @InjectMocks
    OrderService service;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void takeAllTest(){
        when(repository.findAll()).thenReturn(Arrays.asList(new Order(1L, "Burger", "Dan",1000),new Order(2L, "Burger", "Dan",1000)));
        List<Order> orders = service.takeAll();
        Assertions.assertEquals(1L,orders.get(0).getId());
        Assertions.assertEquals(2L,orders.get(1).getId());
    }

    @Test
    public void createNewOrderTest(){
        OrderDto dto = new OrderDto("Burger","Dan",1000);
        when(mapper.sourceToDestination(dto)).thenReturn(new Order(1L,"Burger","Dan",1000));
        Order order = mapper.sourceToDestination(dto);
        when(repository.save(order)).thenReturn(order);
        repository.save(order);
        Assertions.assertEquals(1L,order.getId());
        Assertions.assertEquals("Burger",order.getOrderName());
        verify(mapper,times(1)).sourceToDestination(dto);
        verify(repository,times(1)).save(order);
    }
}
