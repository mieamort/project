package com.example.queuelisten1;

import com.example.queuelisten1.config.RabbitConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(RabbitConfig.class)

public class Queuelisten1Application {

	public static void main(String[] args) {
		SpringApplication.run(Queuelisten1Application.class, args);
	}

}
