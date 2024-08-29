package com.github.agent;

import net.bytebuddy.asm.Advice;

public class PrintMethodAdvice {

    @Advice.OnMethodEnter()
    public static void onEnter() {
        System.out.println("------------------");
    }

}