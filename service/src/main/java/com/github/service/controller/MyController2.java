package com.github.service.controller;

import com.github.agent.common.annotation.Print;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/my2")
public class MyController2 {
    @Print
    @Async
    @GetMapping
    public Object test(String args) {
        System.out.println("Controller Classloader = " + this.getClass().getClassLoader());
        print();
        return "hello world";
    }

    @Print
    private void print() {
        System.out.println("私有方法被调用");
    }
}
