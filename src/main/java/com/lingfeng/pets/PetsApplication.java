package com.lingfeng.pets;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
@EnableTransactionManagement
@SpringBootApplication
@EnableScheduling
@EnableCaching
@MapperScan("com.lingfeng.pets.mapper")
public class PetsApplication extends SpringBootServletInitializer {
	public static void main(String[] args) {
		SpringApplication.run(PetsApplication.class, args);
	}
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(PetsApplication.class);
    }
}
