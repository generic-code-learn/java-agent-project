package com.github.agent;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class AgentClassLoader extends ClassLoader {

    private final String classPath;

    public AgentClassLoader(String classPath) {
        this.classPath = classPath;
    }

    public AgentClassLoader() {
        URL resource = getClass().getResource("/");
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        try {
            return findClass(name);
        } catch (ClassNotFoundException e) {
            return super.loadClass(name);
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        URL resource = getClass().getResource("/");

        String fileName = name.replace('.', '/') + ".class";
        try (InputStream is = getClass().getResourceAsStream(classPath + "/" + fileName);) {
            if (is == null) {
                throw new ClassNotFoundException("Class not found: " + name);
            }
            byte[] bytes = is.readAllBytes();
            return defineClass(name, bytes, 0, bytes.length);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public static void main(String[] args) {
        Class<?> clazz = AgentClassLoader.class;
        URL resourceUrl = clazz.getResource("/"); // 返回当前类所在目录的父目录
        if (resourceUrl != null) {
            System.out.println("Resource URL: " + resourceUrl);
        }
        URL resourceUrls = AgentClassLoader.class.getClassLoader().getResource("");
        ClassLoader classLoader = AgentClassLoader.class.getClassLoader();
        Package[] definedPackages = classLoader.getDefinedPackages();
        if (resourceUrls != null) {
            System.out.println("Resource URL: " + resourceUrls);
        }

    }
}
