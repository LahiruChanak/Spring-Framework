package lk.ijse.bean;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.stereotype.Component;

//@Component -> commented this annotation, and it will annotate at AppConfig class using @Bean annotation.
public class SpringBeanTwo implements BeanNameAware {

    public SpringBeanTwo() {
        System.out.println("SpringBeanTwo Constructor...");
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("SpringBeanTwo Bean Name: " + name);
    }
}
