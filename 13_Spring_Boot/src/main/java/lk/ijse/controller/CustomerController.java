package lk.ijse.controller;

import lk.ijse.dto.CustomerDTO;
import lk.ijse.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping(path = "save")
    public Boolean saveCustomer(@RequestBody CustomerDTO customerDTO) {
        boolean res = customerService.save(customerDTO);
        return res;
    }

    @GetMapping("search/{id}")
    public CustomerDTO getCustomerById(@PathVariable int id) {
        CustomerDTO customerDTO = customerService.getCustomerById(id);

        if (customerDTO != null) {
            return customerDTO;
        } else {
            return null;
        }
    }

    @PutMapping("update")
    public Boolean updateCustomer(@RequestBody CustomerDTO customerDTO) {
        boolean res = customerService.updateCustomer(customerDTO.getId(), customerDTO);

        if (res) {
            return true;
        } else {
            return false;
        }
    }

    @DeleteMapping("delete/{id}")
    public Boolean deleteCustomer(@PathVariable int id) {
        boolean res = customerService.deleteCustomer(id);
        if (res) {
            return true;
        } else {
            return false;
        }
    }

    @GetMapping("getAll")
    public List<CustomerDTO> getAllCustomers() {
        List<CustomerDTO> allCustomers = customerService.getAllCustomers();
        return allCustomers;
    }
}
