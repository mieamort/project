package payroll.controller;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Controller
public class QueueController {
    @Autowired
    AmqpTemplate template;
    @Scheduled(fixedDelay = 1000, initialDelay = 500)
    @RequestMapping("/send/{queue}/{message}")
    @ResponseBody
    String sendingmessage(@PathVariable String message, @PathVariable int queue) throws IOException, TimeoutException {
        if (queue == 1) {
            template.convertAndSend("test.queue", message);
            template.convertAndSend("test.queue", message);
        }else {
            template.convertAndSend("test.queue2", message);
            template.convertAndSend("test.queue2", message);
        }
        return "Сообщение отправлено";
    }


}
