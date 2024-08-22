package com.github.agent.common;

public class JavaAgentCommonVersion {
    public static void printVersion() {
        System.out.println("java-agent-common version 0.0.1");
    }

    public static void main(String[] args) {
        System.out.println(System.getProperty("java.class.path"));
        ClassLoader classLoader = JavaAgentCommonVersion.class.getClassLoader();
        Package[] definedPackages = classLoader.getDefinedPackages();
        for (Package definedPackage : definedPackages) {
            System.out.println(definedPackage.getName());
        }
    }
}
