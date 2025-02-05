package lk.ijse.bean;

import org.springframework.beans.factory.BeanNameAware;

//@Component -> commented this annotation, and it will annotate at AppConfig class using @Bean annotation.
public class SpringBeanThree implements BeanNameAware {

    public SpringBeanThree() {
        System.out.println("SpringBeanThree Constructor...");
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("SpringBeanThree Bean Name: " + name);
    }
}
