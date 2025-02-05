package lk.ijse.bean;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SpringBean implements InitializingBean {

    @Value("${os.name}")
    private String osName;
    @Value("${USERDOMAIN}")
    private String logName;

    @Value("${database.user}")
    private String user;

    @Value("${db.URL}")
    private String url;

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println(osName);
        System.out.println(logName);
        System.out.println(user);
        System.out.println(url);
    }
}