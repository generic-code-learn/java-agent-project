package com.github.agent;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.utility.JavaModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;

/**
 * 转换器
 *
 * @author Misaka
 * @date 2024/08/22
 */
public class Transformer implements AgentBuilder.Transformer {
    private static final Logger LOGGER = LoggerFactory.getLogger(Transformer.class);

    @Override
    public DynamicType.Builder<?> transform(DynamicType.Builder<?> builder, TypeDescription typeDescription, ClassLoader classLoader, JavaModule module) {
        // 方式1
        // return builder.method(ElementMatchers.isAnnotatedWith(ElementMatchers.nameStartsWith("com.github.agent.common.annotation.Print")))
        //         .intercept(MethodDelegation.to(MethodInterceptor.class));

        //  方式2
        Class<?> printClass = null;
        try {
            printClass = classLoader.loadClass("com.github.agent.common.annotation.Print");
        } catch (ClassNotFoundException e) {
            LOGGER.error("load class com.github.agent.common.annotation.Print failed", e);
        }
        if (printClass == null) {
            return builder;
        }
        return builder.method(ElementMatchers.isAnnotatedWith((Class<? extends Annotation>) printClass))
                .intercept(Advice.to(PrintMethodAdvice.class));
    }
}
