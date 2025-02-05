package lk.ijse.controller;

import lk.ijse.dto.CustomerDTO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("json")
public class JSONController {

    @PostMapping
    public String test(@RequestBody CustomerDTO customerDTO) { // unsupported media type error occurs when the request body is not in JSON format. use jackson-databind dependency to convert JSON to a Java object.
        return customerDTO.getFirstName() + " " + customerDTO.getLastName();
    }

    // return a single object. use value attribute to specify the path. I used it because has two GET methods. otherwise it is not necessary.
    @GetMapping(value = "single", produces = {MediaType.APPLICATION_JSON_VALUE})
    public CustomerDTO test2() {
        return new CustomerDTO("Lahiru", "Chanaka", "22");
    }

    // return an array of objects
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ArrayList<CustomerDTO> test3() {
        ArrayList<CustomerDTO> customerDTOS = new ArrayList<>();
        customerDTOS.add(new CustomerDTO("Lahiru", "Chanaka", "22"));
        customerDTOS.add(new CustomerDTO("Kasun", "Chamara", "25"));

        return customerDTOS;
    }
}
