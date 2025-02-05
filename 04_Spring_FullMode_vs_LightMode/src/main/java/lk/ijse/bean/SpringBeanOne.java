package lk.ijse.bean;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

//@Component -> commented this annotation, and it will annotate at AppConfig class using @Bean annotation.
public class SpringBeanOne implements BeanNameAware {

    public SpringBeanOne() {
        System.out.println("SpringBeanOne Constructor...");
    }

    @Bean
    public SpringBeanTwo springBeanTwo(){
        SpringBeanThree bean = springBeanThree();
        SpringBeanThree bean1 = springBeanThree();

        System.out.println(bean);
        System.out.println(bean1);

        return new SpringBeanTwo();
    }

    @Bean
    public SpringBeanThree springBeanThree(){
        return new SpringBeanThree();
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("SpringBeanOne Bean Name: " + name);
    }

}
