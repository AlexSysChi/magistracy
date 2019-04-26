package annotation.example.service;

import annotation.example.annotation.Init;
import annotation.example.annotation.Service;

@Service(name = "lazyServiceAnnotation", lazyLoad = true)
public class LazyService {

    @Init(suppersException = false)
    public void initLazyService() throws RuntimeException {
        System.out.println("lazyInit method from SimpleService");
        throw new RuntimeException("LazyService exception");
    }

    public void doLazy() {
        System.out.println("lazy doing");
    }
}
