package lk.ijse;

import lk.ijse.bean.SpringBeanOne;
import lk.ijse.bean.SpringBeanTwo;
import lk.ijse.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppInitializer {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AppConfig.class);
        context.refresh();

//        SpringBeanOne bean1 = context.getBean(SpringBeanOne.class);
//        System.out.println(bean1);
//
//        SpringBeanOne bean2 = context.getBean(SpringBeanOne.class);
//        System.out.println(bean2);
//
//        SpringBeanTwo bean3 = context.getBean(SpringBeanTwo.class);
//        System.out.println(bean3);
//
//        SpringBeanTwo bean4 = context.getBean(SpringBeanTwo.class);
//        System.out.println(bean4);

//        SpringBeanTwo bean5 = context.getBean(SpringBeanTwo.class);
//        SpringBeanTwo bean6 = context.getBean(SpringBeanTwo.class);
//        System.out.println(bean5);
//        System.out.println(bean6);

        context.registerShutdownHook();
    }

}
