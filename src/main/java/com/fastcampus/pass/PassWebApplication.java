package com.fastcampus.pass;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class PassWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(PassWebApplication.class, args);
    }

}
