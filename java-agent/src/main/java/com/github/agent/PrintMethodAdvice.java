package com.github.agent;

import net.bytebuddy.asm.Advice;
import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.Origin;

import java.lang.reflect.Method;
import java.util.Arrays;

public class PrintMethodAdvice {

    @Advice.OnMethodEnter(suppress = Throwable.class)
    public static void onEnter(@Origin Method method,
                               @AllArguments Object[] args) {
        System.out.println("方法：" + method);
        System.out.println("参数：" + Arrays.toString(args));
    }

}