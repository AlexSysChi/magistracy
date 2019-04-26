package annotation.example.service;

import annotation.example.annotation.Init;
import annotation.example.annotation.Service;

@Service(name = "simpleServiceAnnotation")
public class SimpleService {
    @Init
    public void initSimpleService(){
        System.out.println("init method from SimpleService");
    }

    public void doSimple() {
        System.out.println("simple doing");
    }
}
