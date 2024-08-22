package com.github.agent;

import java.lang.instrument.Instrumentation;

public class PrintAgent {

    // private static final Logger LOGGER = LoggerFactory.getLogger(PrintAgent.class);

    public static void premain(String agentArgs, Instrumentation inst) {
        // LOGGER.info("开始执行premain()");
        // LOGGER.info("开始执行premain()");
        System.out.println("开始执行premain()");
        try {
            ((BootStartup) new AgentClassLoader("")
                    .loadClass("com.github.agent.BootStartup")
                    .getConstructor()
                    .newInstance())
                    .start(agentArgs, inst);
        } catch (Exception e) {
            System.out.println(e);
            // LOGGER.error(e.getMessage(), e);
        }
    }

}