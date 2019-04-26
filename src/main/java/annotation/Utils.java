package annotation;

import com.sun.istack.internal.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Utils {

    public static List<String> getCanonicalNamesClassHierarchy(Class<?> clazz) {
        List<Class<?>> classes = new ArrayList<>();
        collectClassHierarchy(clazz, classes);

        List<String> result = new ArrayList<>();
        for (Class oneClass : classes) {
            result.add(oneClass.getCanonicalName());
        }
        return result;
    }

    private static List<Class<?>> getClassHierarchy(Class<?> clazz) {
        List<Class<?>> result = new ArrayList<>();
        collectClassHierarchy(clazz, result);
        return result;
    }

    private static void collectClassHierarchy(Class<?> clazz, @NotNull List<Class<?>> result) {
        result.add(clazz);
        if (clazz.getSuperclass() != null) {
            collectClassHierarchy(clazz.getSuperclass(), result);
        } else {
            Collections.reverse(result);
        }
    }
}
