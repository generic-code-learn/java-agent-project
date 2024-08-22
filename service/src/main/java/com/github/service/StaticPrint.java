package com.github.service;

public class StaticPrint {
    static {
        System.out.println("static print");
        System.out.println(StaticPrint.class.getClassLoader());
    }
}
