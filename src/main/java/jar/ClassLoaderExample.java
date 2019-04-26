package jar;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Paths;

public class ClassLoaderExample {

    public static void main(String[] args) throws MalformedURLException {
        URL jarFileURL = Paths.get("jar.JarClass.jar").toUri().toURL();
        ClassLoader classLoader = new URLClassLoader(new URL[] {jarFileURL});

        try {
            Class clazz = classLoader.loadClass("jar.JarClass");
            System.out.println(clazz.getSimpleName());

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
