package com.example.sbintern3;

import com.example.sbintern3.config.RabbitConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;


@SpringBootApplication
@Import(RabbitConfig.class)
public class SbIntern3Application {

	public static void main(String[] args) {
		SpringApplication.run(SbIntern3Application.class, args);
	}
//	@Bean
//	public CommandLineRunner tutorial() {
//		return new RabbitRunner();
//	}

}
