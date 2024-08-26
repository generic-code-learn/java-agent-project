package com.github.logging;


import com.github.agent.AgentClassLoader;
import com.github.agent.ClassPathUtil;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class AgentClassLoaderTest {

    @Test
    public void testSecondTest() throws MalformedURLException, ClassNotFoundException {
        URL url = new URL("file:/E:/Project/java-agent-project/java-agent/target/java-agent-lib.jar");
        URL[] classPath = ClassPathUtil.findNestedClassPath(url);
        AgentClassLoader agentClassLoader = new AgentClassLoader(classPath);
        Class<?> loadedClass = agentClassLoader.loadClass("com.github.agent.BootStartup");
        System.out.println(loadedClass);
    }
}