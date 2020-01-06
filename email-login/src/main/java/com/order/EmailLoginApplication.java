package com.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Mrchen
 */
@SpringBootApplication
@ComponentScan(value = {"com.order.util", "com.order.controller",
        "com.order.mapper","com.order.service","com.order.config"})
public class EmailLoginApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmailLoginApplication.class, args);
    }
}
