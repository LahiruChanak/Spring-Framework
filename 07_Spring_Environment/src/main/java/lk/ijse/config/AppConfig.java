package lk.ijse.config;

import org.springframework.context.annotation.*;

@Configuration
@ComponentScan(basePackages = "lk.ijse.bean")
@PropertySource("classpath:application.properties")
public class AppConfig {

}