package payroll.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import payroll.client.OrderClient;
import payroll.dto.OrderDto;

import java.util.List;


@Service
@Log4j2
public class OrderService {
    @Autowired
    OrderClient orderClient;

    @Retryable(backoff = @Backoff(delay = 5000), value = {RuntimeException.class},maxAttempts = 3)
    public List<OrderDto> retrieveOrderList() {
//        OrderListResponse response = restTemplate.getForObject("http://localhost:8090/orders/all", OrderListResponse.class);
        return orderClient.retrieveOrderList();

    }

    @Retryable(backoff = @Backoff(delay = 20000), value = {RuntimeException.class}, maxAttempts = 3)
    public ResponseEntity<String> postOrder(OrderDto dto) {
        return orderClient.postOrder(dto);
    }
    @Recover
    public void recoverFromFixedRetry(RuntimeException ex, String requestId) {
        log.info("Recovering request {}", requestId);
    }
}
