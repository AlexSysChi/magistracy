package annotation.example;

import annotation.example.annotation.Init;
import annotation.example.annotation.Service;
import annotation.example.service.SimpleService;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ProcessAnnotation {

    private static Map<String, Object> serviceMap = new HashMap<>();
    private static Map<String, Boolean> initializesServices = new HashMap<>();

    public static void main(String[] args) {

        String[] classNames = {
                "annotation.example.service.LazyService",
                "annotation.example.service.SimpleService",
                "java.lang.String"};

        for (String className : classNames) {
            loadService(className);
        }
        //System.out.println(serviceMap);

        if (serviceMap.size() > 0) {
            SimpleService simpleService = (SimpleService) serviceMap
                    .get("simpleServiceAnnotation");
            //System.out.println(simpleService.getS());
            //System.out.println(simpleService.getI());
        }

        System.out.println(initializesServices.size());

//        checkClass(SimpleService.class);
//        checkClass(LazyService.class);
//        checkClass(String.class);

//        inspectService(SimpleService.class);
//        inspectService(LazyService.class);
//        inspectService(String.class);
    }


    private static void loadService(String className) {

        Class clazz = null;
        try {
            clazz = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (Objects.requireNonNull(clazz).isAnnotationPresent(Service.class)) {
            Service annotation = (Service) clazz.getAnnotation(Service.class);
            if (!annotation.lazyLoad()) {
                Object service = null; // or through constructor

                //            //trough constructor
//            Constructor[] constructors = clazz.getConstructors();
//            for (Constructor c : constructors) {
//                if (c.getParameters().length == 0) {
//                    System.out.println("[" + clazz.getSimpleName() + "] has a default constructor");
//                } else {
//                    System.out.println("[" + clazz.getSimpleName() + "] has a constructor with parameters: ");
//                    Arrays.stream(c.getParameters())
//                            .forEach(p -> System.out.println("\t" + p.getType().getSimpleName()));
//                }
//            }

                try {
                    service = clazz.newInstance();
                    initMethod(service);

                } catch (InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
                serviceMap.put(annotation.name(), service);
            }
        }
        System.out.println();
    }

    private static void initMethod(Object object) {
        Method[] methods = object.getClass().getMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Init.class)) {
                try {
                    method.invoke(object);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    Init annotation = method.getAnnotation(Init.class);
                    if (annotation.suppersException()) {
                        System.err.println("suppressed exception:" + e.getCause().getMessage());
                    } else {
                        throw new RuntimeException(e.getCause().getMessage(), e);
                    }
                }
                initializesServices.put(object.getClass().getAnnotation(Service.class).name(), true);
            }
        }
    }

    private static void checkClass(Class<?> service) {
        System.out.println("Checking class: [" + service + "] " +
                "\n\tname: " + service.getName() +
                "\n\tcanonical name: " + service.getCanonicalName() +
                "\n\tsimple name: " + service.getSimpleName());
        boolean isAnnotation = service.isAnnotation();
        System.out.println("\tis annotation: " + isAnnotation);
        //------------------------------------------------------
        boolean containsAnnotation = service.isAnnotationPresent(Service.class);
        System.out.println("\tcontains annotation [@Service]: " + containsAnnotation + "\n");
        if (containsAnnotation) {
            Service annotation = service.getAnnotation(Service.class);

            System.out.println("\t\tannotation [@Service] class name: " + annotation.getClass().getName());
            System.out.println("\t\tannotation [@Service] class canonical name: " + annotation.getClass().getCanonicalName());
            System.out.println("\t\tannotation [@Service] class simple name: " + annotation.getClass().getSimpleName() + "\n");

            System.out.println("\t\tannotation [@Service] type name: " + annotation.annotationType().getName());
            System.out.println("\t\tannotation [@Service] type canonical name: " + annotation.annotationType().getCanonicalName());
            System.out.println("\t\tannotation [@Service] type simple name: " + annotation.annotationType().getSimpleName() + "\n");

            System.out.println("\t\tannotation [@Service] details:");
            System.out.println("\t\t\tannotation name = " + annotation.name());
            System.out.println("\t\t\tannotation lazyLoad = " + annotation.lazyLoad() + "\n");

//            Field[] fields = annotation.getClass().getFields();
//            Arrays.stream(fields)
//                    .forEach(field -> System.out.println("field " + field.getName()));
//
//            Field[] declaredFields = annotation.getClass().getDeclaredFields();
//            Arrays.stream(declaredFields)
//                    .forEach(field -> System.out.println("field " + field.getName()));
//            Method[] declaredMethods = annotation.getClass().getDeclaredMethods();
//            Arrays.stream(declaredMethods)
//                    .forEach(method -> System.out.println("\t\t\t" + method.getName()));
        }
    }

    //    private static void inspectService(Class<?> service) {
//        if (service.isAnnotationPresent(Service.class)){
//
//            final Service classAnnotation = service.getAnnotation(Service.class);
//            if (classAnnotation.lazyLoad()) {
//
//            } else {
//
//            }
//
//            Method[] methods = service.getMethods();
//            for (Method method: methods) {
//                if (method.isAnnotationPresent(Init.class)) {
//                    System.out.println("Method [" + method.getName() + "] in [" + service.getSimpleName() + "]");
//                    final Init annotation = method.getAnnotation(Init.class);
//                    System.out.println("\tSuppersException: " +  annotation.suppersException());
//
//
//
//                } else {
//                    //System.out.println(" Method [" + method.getName() + "] in [" + service.getSimpleName() + "]");
//                }
//            }
//
////
////            System.out.println("\n" + annotation.getClass().getSimpleName() + " found in service: ["
////                    + service.getSimpleName() + "]");
////            System.out.println("\tannotation name = " + annotation.name());
////            System.out.println("\tannotation lazyLoad = " + annotation.lazyLoad());
//        } else {
//            System.out.println("\nservice: [" + service.getSimpleName() + "] doesn't contains required annotation");
//        }
//    }

}
