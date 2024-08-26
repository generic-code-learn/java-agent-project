package com.github.agent;

import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.SuperCall;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;

public class PrintMethodInterceptor {
    public static final Logger LOGGER = LoggerFactory.getLogger(PrintMethodInterceptor.class);

    @RuntimeType
    public static Object intercept(@Origin Method method,
                                   @AllArguments Object[] args,
                                   @SuperCall Callable<?> callable) throws Exception {
        LOGGER.info("拦截到方法：{}", method.getName());
        LOGGER.info("方法参数：{}", args);
        return callable.call();
    }

}