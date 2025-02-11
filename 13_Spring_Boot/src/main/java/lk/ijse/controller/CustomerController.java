package lk.ijse.controller;

import lk.ijse.dto.CustomerDTO;
import lk.ijse.entity.Customer;
import lk.ijse.repository.CustomerRepo;
import lk.ijse.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/customer", produces = "application/json")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerRepo customerRepo;

    @PostMapping(path = "/save")
    public boolean getCustomer(@RequestBody CustomerDTO dto) {
        customerRepo.save(new Customer(
                dto.getId(),
                dto.getName(),
                dto.getAddress()
        ));

        return true;
    }
}
