package com.dionext;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan
@ComponentScan(basePackages = "com.dionext")
public class DioMsAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(DioMsAppApplication.class, args);
    }

}

