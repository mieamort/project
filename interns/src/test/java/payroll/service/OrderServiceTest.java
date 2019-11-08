package payroll.service;


import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import payroll.client.OrderClient;
import payroll.dto.OrderDto;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

@SpringBootTest
public class OrderServiceTest {

    @Mock
    OrderClient client;

    @InjectMocks
    OrderService service;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void retrieveOrderListTest() {
        when(client.retrieveOrderList()).thenReturn(Arrays.asList(new OrderDto("Burger", 1L, "Dan", 1000), new OrderDto("Burger", 2L, "Dan", 1000), new OrderDto("Burger", 3L, "Dan", 1000)));
        List<OrderDto> ordersDto = service.retrieveOrderList();
        Assertions.assertEquals(1l, ordersDto.get(0).getId());
        Assertions.assertEquals(2l, ordersDto.get(1).getId());
        Assertions.assertEquals(3l, ordersDto.get(2).getId());
        verify(service, times(1)).retrieveOrderList();
    }


    @Test
    public void postOrderTest() {
        OrderDto dto = new OrderDto("Burger", 1L, "Dan", 1000);
        when(client.postOrder(dto)).thenReturn(ResponseEntity.ok("Ok"));


        Assertions.assertEquals(ResponseEntity.ok("Ok"), client.postOrder(dto));
        verify(client, times(1)).postOrder(dto);
    }

}
