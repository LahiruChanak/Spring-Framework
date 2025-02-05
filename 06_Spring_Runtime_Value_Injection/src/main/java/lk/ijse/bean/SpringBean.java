package lk.ijse.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SpringBean {

    public SpringBean() {
        System.out.println("SpringBean Created");
    }

    public SpringBean(@Value("Lahiru") String name, @Value("1") int id, @Value("true") boolean isActive) {
        System.out.println("SpringBean Created");
        System.out.println("ID: " + id + "\nName: " + name + "\nActive: " + isActive);
    }

    @Autowired(required = false) //
    public SpringBean(@Value("Lahiru") String name, @Value("1") int id) {
        System.out.println("SpringBean Created");
        System.out.println("ID: " + id + "\nName: " + name);
    }

}
