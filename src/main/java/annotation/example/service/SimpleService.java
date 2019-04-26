package annotation.example.service;

import annotation.example.annotation.Init;
import annotation.example.annotation.Service;

@Service(name = "simpleServiceAnnotation")
public class SimpleService {

    private String s = "default";
    private int i = 5;

    public SimpleService() {
    }

    public SimpleService(String s) {
        this.s = s;
    }

    public SimpleService(int i) {
        this.i = i;
    }

    public SimpleService(String s, int i) {
        this.s = s;
        this.i = i;
    }

    @Init(suppresException = false)
    public void initSimpleService() throws RuntimeException{
        System.out.println("init method from SimpleService");
        throw new RuntimeException("SimpleService exception");

    }

    public void doSimple() {
        System.out.println("simple doing");
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }
}
