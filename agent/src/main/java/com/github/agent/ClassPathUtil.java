package com.github.agent;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.CodeSource;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ClassPathUtil {
    private static final String DEFAULT_LIB_LOCATION = "lib/";

    public static URL[] findClassPath() {
        URL[] urls = new URL[2];
        URL codeSourceLocation;
        CodeSource codeSource = AgentClassLoader.class.getProtectionDomain().getCodeSource();
        if (codeSource != null) {
            codeSourceLocation = codeSource.getLocation();
        } else {
            throw new NoClassDefFoundError();
        }
        urls[0] = codeSourceLocation;
        try {
            urls[1] = new URL("jar:" + codeSourceLocation.toExternalForm() + "!/" + DEFAULT_LIB_LOCATION);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        return urls;
    }

    /**
     * 查找嵌套类路径，如JAR-IN-JAR
     *
     * @param baseLocation 基础路径
     * @return {@link URL[] }
     */
    public static URL[] findNestedClassPath(URL baseLocation) {
        List<URL> urls = new ArrayList<>();
        urls.add(baseLocation);
        try (JarFile jarFile = new JarFile(baseLocation.getFile())) {
            Enumeration<JarEntry> entries = jarFile.entries();
            while (entries.hasMoreElements()) {
                JarEntry entry = entries.nextElement();
                String entryName = entry.getName();
                if (entryName.startsWith(DEFAULT_LIB_LOCATION) && !entry.isDirectory() && entryName.endsWith(".jar")) {
                    urls.add(new URL("jar:" + baseLocation + "!/" + entryName + "!/"));
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Java Agent Nested依赖加载失败", e);
        }
        return urls.toArray(new URL[]{});
    }
}
