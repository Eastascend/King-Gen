package com.kevinwong;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.kevinwong.dao")
public class KingGenApplication {

	public static void main(String[] args) {
		SpringApplication.run(KingGenApplication.class, args);
	}
}
