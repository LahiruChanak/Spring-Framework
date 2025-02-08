package lk.ijse.controller;

import lk.ijse.dto.CustomerDTO;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/customer")
public class CustomerController {

    private List<CustomerDTO> customers = new ArrayList<>();

    @PostMapping(path = "save")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO) {
        customers.add(customerDTO);
        return customerDTO;
    }

    @GetMapping(path = "getAll")
    public List<CustomerDTO> getCustomer() {
        return customers;
    }

    @PutMapping(path = "update")
    public CustomerDTO updateCustomer(@RequestBody CustomerDTO customerDTO) {

        for (int i = 0; i < customers.size(); i++) {

            CustomerDTO existingCustomer = customers.get(i);

            if (existingCustomer.getId().equals(customerDTO.getId())) {
                existingCustomer.setName(customerDTO.getName());
                existingCustomer.setAddress(customerDTO.getAddress());
                existingCustomer.setAge(customerDTO.getAge());
                existingCustomer.setProfilePic(customerDTO.getProfilePic());
                return customerDTO;
            }
        }
        return customerDTO;
    }

    @DeleteMapping(path = "delete/{id}")
    public boolean deleteCustomer(@PathVariable(value = "id") String id) {
        for (int i = 0; i < customers.size(); i++) {
            CustomerDTO existingCustomer = customers.get(i);
            if (existingCustomer.getId().equals(id)) {
                customers.remove(i);
                return true;
            }
        }
        return false;
    }
}
