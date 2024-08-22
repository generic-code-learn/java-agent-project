package com.github.agent;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.matcher.ElementMatchers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.instrument.Instrumentation;

/**
 * @author 杨中肖
 * @date 2024/08/22
 */
public class BootStartup {
    private static final Logger LOGGER = LoggerFactory.getLogger(BootStartup.class);

    static {
        LOGGER.info("BootStartup ClassLoader = {}", BootStartup.class.getClassLoader());
    }

    public void start(String agentArgs, Instrumentation inst) {
        new AgentBuilder.Default()
                .type(ElementMatchers.nameStartsWith("com.github"))
                .transform(new Transformer())
                .installOn(inst);
    }
}
