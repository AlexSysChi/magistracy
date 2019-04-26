package annotation.example.service;

import annotation.example.annotation.Init;
import annotation.example.annotation.Service;

@Service(name = "lazyServiceAnnotation", lazyLoad = true)
public class LazyService {
    @Init()
    public void initLazyService() throws Exception {
        System.out.println("lazyI1nit method from SimpleService");
    }

    public void doLazy() {
        System.out.println("lazy doing");
    }
}
