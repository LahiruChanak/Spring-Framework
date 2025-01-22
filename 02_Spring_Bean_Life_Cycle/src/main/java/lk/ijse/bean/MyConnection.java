package lk.ijse.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class MyConnection implements BeanNameAware, BeanFactoryAware, ApplicationContextAware, InitializingBean {
    public MyConnection() {
        System.out.println("MyConnection Created");
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("My Connection Bean name set");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("My Connection Bean factory set");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("My Connection Application context set");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("My Connection Properties set");
    }

//    This method is working when implemented DisposableBean to MyConnection class
//    @Override
//    public void destroy() throws Exception {
//        System.out.println("MyConnection Destroyed");
//    }

}
