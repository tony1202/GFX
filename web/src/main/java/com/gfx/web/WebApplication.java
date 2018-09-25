package com.gfx.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = "com.gfx.web")
@EnableTransactionManagement
@Cacheable
public class WebApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
        // SpringApplication springApplication = new SpringApplication(WebApplication.class);
        // springApplication.run(args);
        //new SpringApplicationBuilder().sources(WebApplication.class).build().run(args);
    }
}
