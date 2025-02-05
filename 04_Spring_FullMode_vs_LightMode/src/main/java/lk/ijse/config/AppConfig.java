package lk.ijse.config;

import lk.ijse.bean.SpringBeanOne;
import lk.ijse.bean.SpringBeanThree;
import lk.ijse.bean.SpringBeanTwo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "lk.ijse.bean")
public class AppConfig {

    // Inter bean dependencies -> call another bean method inside a bean method.
//    @Bean
//    public SpringBeanOne getSpringBeanOne(){
//        SpringBeanTwo bean1 = getSpringBeanTwo();
//        SpringBeanTwo bean2 = getSpringBeanTwo();
//        return new SpringBeanOne();
//    }
//
//    @Bean
//    public SpringBeanTwo getSpringBeanTwo(){
//        return new SpringBeanTwo();
//    }

    @Bean
    public SpringBeanTwo getSpringBeanTwo(){
        SpringBeanThree bean = getSpringBeanThree();
        SpringBeanThree bean1 = getSpringBeanThree();
        System.out.println(bean);
        System.out.println(bean1);

        return new SpringBeanTwo();
    }

    @Bean
    public SpringBeanThree getSpringBeanThree(){
        return new SpringBeanThree();
    }
}
