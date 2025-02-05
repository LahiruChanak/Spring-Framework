package lk.ijse.config;

import lk.ijse.bean.SpringBean;
import org.springframework.context.annotation.*;

@Configuration
//@ComponentScan(basePackages = "lk.ijse")
@Import(AppConfigOne.class) // Importing another configuration class. Second way to configure
//@ImportResource("classpath:beans.xml") // Importing beans.xml file if it is in the root directory. Third way to configure.
//@ImportResource("file:absolute path of beans.xml") // Importing beans.xml file if it isn't in root directory. Third way to configure.
public class AppConfig {

    @Bean
    public SpringBean springBean(){
        return new SpringBean();
    }

}
