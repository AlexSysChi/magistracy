package annotation;

import java.lang.annotation.*;

//annotation behaviour description
@Documented // appear in Javadoc for classes with such annotation
@Inherited // inherited by descendants (all subclasses/implementations also will have such annotation)
@Target(ElementType.TYPE) // means that application area is classes and interfaces
                          //ElementType.PACKAGE - it means for all classes in package
@Retention(RetentionPolicy.RUNTIME) // life cycle
                                        // SOURCE -  for documentation
                                        //CLASS - until compiler works, before runtime

public @interface AnnotationDeclaration {

    // possible annotation property types: primitives, Strings, arrays, NOT complex reference types
    // annotation methods: - have no arguments
    // if we declare a field annotation property, like in usual interface,  it has to be initialized
    // not mandatory fields declared through methods with default value declaration

    // seems not used
    String prop1 = "propOne";

    String prop2(); // have to be set

    boolean prop3() default false;

    int prop4() default 0;

    int[] prop5();

    //Map prop6(); // invalid type '' for annotation member: annotation - is meta info => couldn't be compiled =>
    // it is impossible to create difficult ref typ


}
