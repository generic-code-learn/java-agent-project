package com.github.agent;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.logging.Logger;

public class AgentClassLoader extends URLClassLoader {
    private static final Logger JULOGGER = Logger.getLogger(AgentClassLoader.class.getName());

    public AgentClassLoader(URL[] classPathUrl) {
        this(classPathUrl, ClassLoader.getSystemClassLoader());
    }

    public AgentClassLoader(URL[] classPathUrl, ClassLoader parent) {
        super(classPathUrl, parent);
    }


    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        try {
            Class<?> loadedClass = findLoadedClass(name);
            if (loadedClass != null) {
                return loadedClass;
            }
        } catch (Exception e) {
            // ignore
        }

        try {
            return findClass(name);
        } catch (Exception e) {
            // ignore
        }

        return super.loadClass(name);
    }


    @Deprecated
    public Class<?> loadClass(String name, URL URL) throws ClassNotFoundException {
        System.out.println("加载包：" + name);
        Class<?> loadedClass = findLoadedClass(name);
        if (loadedClass != null) {
            System.out.println("已加载包：" + name);
            return loadedClass;
        }
        String filePath = "jar:" + URL.toExternalForm() + "!" + "/" + name.replace(".", "/") + ".class";
        System.out.println("filePath = " + filePath);
        try (InputStream inputStream = new URL(filePath).openStream()) {
            System.out.println(inputStream.available());
            if (inputStream == null) {
                loadedClass = getParent().loadClass(name);
            } else {
                byte[] bytes = inputStream.readAllBytes();
                loadedClass = defineClass(name, bytes, 0, bytes.length);
                System.out.println("已加载：" + name);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(name);
        }
        return loadedClass;
    }
}
