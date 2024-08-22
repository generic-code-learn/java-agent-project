package com.github.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

import java.io.IOException;

@EnableAsync
@SpringBootApplication
public class Application {
    public static void main(String[] args) throws IOException {
        System.in.read();
        SpringApplication.run(Application.class, args);
    }
}
