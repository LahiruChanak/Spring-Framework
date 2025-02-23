package lk.ijse.controller;

import lk.ijse.dto.CustomerDTO;
import lk.ijse.service.impl.CustomerServiceImpl;
import lk.ijse.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/customer")
@CrossOrigin(origins = "*")
public class CustomerController {

    @Autowired
    private CustomerServiceImpl customerService;

    @PostMapping(path = "save")
    public ResponseUtil saveCustomer(@RequestBody CustomerDTO customerDTO) {
        customerService.saveCustomer(customerDTO);
        return new ResponseUtil(201, "Customer Saved", null);

    }

    @GetMapping("search/{id}")
    public ResponseUtil getCustomerById(@PathVariable int id) {
        return new ResponseUtil(200, "Success", customerService.getCustomerById(id));
    }

    @PutMapping("update")
    public ResponseUtil updateCustomer(@RequestBody CustomerDTO customerDTO) {
        customerService.updateCustomer(customerDTO);
        return new ResponseUtil(200, "Customer Updated", null);

    }

    @DeleteMapping("delete/{id}")
    public ResponseUtil deleteCustomer(@PathVariable int id) {
        customerService.deleteCustomer(id);
        return new ResponseUtil(200, "Customer Deleted Successfully", null);

    }

    @GetMapping("getAll")
    public ResponseUtil getAllCustomers() {
        return new ResponseUtil(200, "Success", customerService.getAllCustomers());
    }
}
