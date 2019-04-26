package annotation.example.service;

import annotation.example.annotation.Init;
import annotation.example.annotation.Service;

@Service(name = "lazyServiceAnnotation", lazyLoad = true)
public class LazyService {

    @Init(suppresException = true)
    public void initLazyService() throws RuntimeException {
        System.out.println("lazyInit method from SimpleService");
        throw new RuntimeException("****");
    }

    public void doLazy() {
        System.out.println("lazy doing");
    }
}
