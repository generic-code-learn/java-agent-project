package com.github.agent;

import com.sun.jdi.Method;

import java.lang.instrument.Instrumentation;
import java.util.function.Consumer;

public class PrintAgent {

    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("开始执行premain()");
        try {
            ((Consumer) new AgentClassLoader()
                    .loadClass("com.github.agent.BootStartup")
                    .getConstructor()
                    .newInstance())
                    .accept(inst);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}