package com.github.service;

import com.github.agent.common.JavaAgentCommonVersion;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;

import java.io.IOException;

@EnableAsync
@SpringBootApplication
public class Application {
    public static void main(String[] args) throws IOException {
        System.in.read();
        StaticPrint staticPrint = new StaticPrint();
        System.out.println(staticPrint);
        SpringApplication.run(Application.class, args);
    }
}
