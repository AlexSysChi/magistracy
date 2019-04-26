package reflection;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Collection;

public class ReflectionWorkflow {

    public static void main(String[] args) throws Exception {
        Class<?> multiSetImplClass = Class.forName("reflection.MultiSetImpl");

        boolean implementsMultiSet = MultiSet.class.isAssignableFrom(multiSetImplClass);
        System.out.println("Implements MultiSet interface: " + implementsMultiSet);

        Constructor<?> defaultConstructor = null;
        Constructor<?> collectionConstructor = null;

        for (Constructor<?> constructor : multiSetImplClass.getConstructors()) {
            Class<?>[] parameterTypes = constructor.getParameterTypes();
            if (parameterTypes.length == 0) {
                defaultConstructor = constructor;
            } else if (parameterTypes.length == 1 && parameterTypes[0].equals(Collection.class)) {
                collectionConstructor = constructor;
            }
        }
        System.out.println("Default constructor present: " + (defaultConstructor != null));
        System.out.println("Constructor from collection present: " + (collectionConstructor != null));

        Class<?> superclass = multiSetImplClass.getSuperclass();
        if (!Object.class.equals(superclass)) {
            System.out.println("Extends " + superclass.getCanonicalName());
        }

        System.out.println("Fields:");
        Class<?> currentClass = multiSetImplClass;
        do {
            for (Field field : currentClass.getDeclaredFields()) {
                // choose not static fields
                if (true && ((field.getModifiers() & Modifier.STATIC) == 0)) {



                    System.out.println(
                            "- " + Modifier.toString(field.getModifiers()) + " "
                                    + field.getType().getCanonicalName() + " " + field.getName()
                                    + " (in " + currentClass.getCanonicalName() + ")");

                    System.out.println("\tfield.getModifiers(): \n\tnumeric: "
                            + field.getModifiers() + "\n\tbinary: "
                            + StringUtils.leftPad(Integer.toBinaryString(field.getModifiers()), 16, '0'));

                    System.out.println("\t" + StringUtils.leftPad(Integer.toBinaryString(field.getModifiers()), 16, '0') + "\n\t&\n\t" +
                            StringUtils.leftPad(Integer.toBinaryString(Modifier.STATIC), 16, '0') + "\n\t=\n\t" +
                            StringUtils.leftPad(Integer.toBinaryString((field.getModifiers() & Modifier.STATIC)), 16, '0'));



                }
            }
            currentClass = currentClass.getSuperclass();
        } while (!Object.class.equals(currentClass));


        MultiSet multiSetInstance = (MultiSet) defaultConstructor.newInstance();
    }
}
