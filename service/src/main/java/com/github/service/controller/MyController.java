package com.github.service.controller;

import com.github.logging.common.annotation.Print;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/my")
public class MyController {
    @Print
    @GetMapping
    public Object test(String args) {
        print();
        return "hello world";
    }

    @Print
    private void print() {
        System.out.println("私有方法被调用");
    }
}
