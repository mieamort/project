package com.example.queuelisten1.queue;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
@RabbitListener(queues = "test.queue2")
public class Consumer {


    @RabbitHandler
    public void receive(String in) {

        StopWatch watch = new StopWatch();
        watch.start();
        System.out.println(" [x] Received '" + in + "'");
        watch.stop();
    }

}

