package com.github.agent;

import java.lang.instrument.Instrumentation;

public class PrintAgent {

    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("开始执行premain()");
        try {
            ClassLoader agentClassLoader = new AgentClassLoader(ClassPathUtil.findClassPath());
            Class<?> bootStartupClass = Class.forName("com.github.agent.BootStartup", false, agentClassLoader);
            bootStartupClass.getDeclaredMethod("start", String.class, Instrumentation.class)
                    .invoke(null, agentArgs, inst);
        } catch (Exception e) {
            throw new AgentException(e);
        }
    }
}