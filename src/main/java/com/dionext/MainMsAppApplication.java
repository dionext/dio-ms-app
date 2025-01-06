package com.dionext;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;

import java.util.Locale;


//@SpringBootApplication(exclude = {SecurityAutoConfiguration.class, ManagementWebSecurityAutoConfiguration.class})
@ComponentScan
@ComponentScan(basePackages = "com.dionext.utils")
@ComponentScan(basePackages = "com.dionext.site")
@Slf4j
@EnableCaching
public class MainMsAppApplication implements ApplicationRunner {

    public static void main(String[] args) {
        Locale.setDefault(Locale.ENGLISH);
        SpringApplication.run(MainMsAppApplication.class, args);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> log.debug("ShutdownHook...")));
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.debug("Application running");
    }

}
