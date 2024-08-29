package com.github.agent;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.MethodDelegation;
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
    private static final String PRINT_CLASS_NAME = "com.github.logging.common.annotation.Print";

    @Override
    public DynamicType.Builder<?> transform(DynamicType.Builder<?> builder, TypeDescription typeDescription, ClassLoader classLoader, JavaModule module) {
        System.out.println(typeDescription);
        System.out.println(classLoader);
        // 方式1
        // return builder.method(ElementMatchers.isAnnotatedWith(ElementMatchers.nameStartsWith(PRINT)))
        //         .intercept(MethodDelegation.to(PrintMethodInterceptor.class));
        // 方式1
        return builder.visit(Advice.to(PrintMethodAdvice.class)
                .on(ElementMatchers.isAnnotatedWith(ElementMatchers.nameStartsWith(PRINT_CLASS_NAME))));
        //  方式2
        // Class<?> printClass = null;
        // try {
        //     printClass = classLoader.loadClass(PRINT_CLASS_NAME);
        // } catch (ClassNotFoundException e) {
        //     LOGGER.error("load class Print failed", e);
        // }
        // if (printClass == null) {
        //     return builder;
        // }
        // return builder.method(ElementMatchers.isAnnotatedWith((Class<? extends Annotation>) printClass))
        //         .intercept(MethodDelegation.to(PrintMethodInterceptor.class));
    }
}
