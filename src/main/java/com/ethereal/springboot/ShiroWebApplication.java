package com.ethereal.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = "com.ethereal.springboot.mapper")
@SpringBootApplication
public class ShiroWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShiroWebApplication.class, args);
	}
}
