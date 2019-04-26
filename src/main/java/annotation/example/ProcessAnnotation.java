package annotation.example;

import annotation.example.annotation.Init;
import annotation.example.annotation.Service;
import annotation.example.service.LazyService;
import annotation.example.service.SimpleService;
import com.sun.istack.internal.NotNull;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;

public class ProcessAnnotation {

    public static void main(String[] args) {

//        checkService(SimpleService.class);
//        checkService(LazyService.class);
//        checkService(String.class);

//        List<Class<?>> stringTree = new ArrayList<>();
//        getClassHierarchy(String.class, stringTree);
//
//        for (Class clazz: stringTree) {
//            System.out.println(clazz.getSimpleName());
//        }


        inspectService(SimpleService.class);
        inspectService(LazyService.class);
        inspectService(String.class);
    }

    private static void inspectService(Class<?> service) {
        if (service.isAnnotationPresent(Service.class)){

            final Service classAnnotation = service.getAnnotation(Service.class);
            if (classAnnotation.lazyLoad()) {

            } else {

            }

            Method[] methods = service.getMethods();
            for (Method method: methods) {
                if (method.isAnnotationPresent(Init.class)) {
                    System.out.println("Method [" + method.getName() + "] in [" + service.getSimpleName() + "]");
                    final Init annotation = method.getAnnotation(Init.class);
                    System.out.println("\tSuppersException: " +  annotation.suppresException());



                } else {
                    //System.out.println(" Method [" + method.getName() + "] in [" + service.getSimpleName() + "]");
                }
            }




//
//            System.out.println("\n" + annotation.getClass().getSimpleName() + " found in service: ["
//                    + service.getSimpleName() + "]");
//            System.out.println("\tannotation name = " + annotation.name());
//            System.out.println("\tannotation lazyLoad = " + annotation.lazyLoad());
        } else {
            System.out.println("\nservice: [" + service.getSimpleName() + "] doesn't contains required annotation");
        }
    }

    private static void checkService(Class<?> service) {
        System.out.println("\nChecking [" + service.getSimpleName() + "] ... ");
        if (service.isAnnotationPresent(Service.class)) {

            Service annotation = service.getAnnotation(Service.class);
            System.out.println(">>>\t" + annotation.getClass().getSimpleName() + " is found!");

            System.out.println(">>>\tservice.getAnnotations():");
            Annotation[] allAnnotations = service.getAnnotations(); // all annotations including ancestor's declaration
            for (Annotation ann: allAnnotations) {
                printAnnotationDetails(annotation, ann);
            }

            System.out.println(">>>\tservice.getDeclaredAnnotations():");
            Annotation[] declaredAnnotations = service.getDeclaredAnnotations(); // 'class' declared annotations
            for (Annotation ann: declaredAnnotations) {
                printAnnotationDetails(annotation, ann);
            }
        } else {
            System.out.println("\nservice: [" + service.getSimpleName() + "] doesn't contains required annotation");
        }

    }

    private static void printAnnotationDetails(Service annotation, Annotation ann) {
        System.out.println("\t\t" + ann.getClass().getName());
        System.out.println("\t\t" + ann.getClass().getCanonicalName());
        System.out.println("\t\t" + ann.getClass().getSimpleName());
        System.out.println("\t\t\tannotation name = " + annotation.name());
        System.out.println("\t\t\tannotation lazyLoad = " + annotation.lazyLoad());
    }

    private static void getClassHierarchy(Class<?> clazz, @NotNull List<Class<?>> result) {
        result.add(clazz);
        if (clazz.getSuperclass() != null) {
            getClassHierarchy(clazz.getSuperclass(), result);
        } else {
           Collections.reverse(result);
        }
    }
}
