package com.oycl.demo;

import com.oycl.demo.common.async2.annotation.EnableAsyncService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAsyncService
@SpringBootApplication
public class AsynbootDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AsynbootDemoApplication.class, args);
	}

}
