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
        boolean res = customerService.saveCustomer(customerDTO);

        if (res) {
            return new ResponseUtil(201, "Customer Saved", null);
        }
        return new ResponseUtil(409, "Customer Already Exists.", null);

    }

    @GetMapping("search/{id}")
    public ResponseUtil getCustomerById(@PathVariable int id) {

        return new ResponseUtil(200, "Success", customerService.getCustomerById(id));
    }

    @PutMapping("update")
    public ResponseUtil updateCustomer(@RequestBody CustomerDTO customerDTO) {
        boolean res = customerService.updateCustomer(customerDTO);

        if (res) {
            return new ResponseUtil(200, "Customer Updated", null);
        } else {
            return new ResponseUtil(400, "Customer Not Updated", null);
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseUtil deleteCustomer(@PathVariable int id) {
        boolean res = customerService.deleteCustomer(id);

        if (res) {
            return new ResponseUtil(200, "Customer Deleted Successfully", null);
        } else {
            return new ResponseUtil(400, "Customer Not Deleted", null);
        }
    }

    @GetMapping("getAll")
    public ResponseUtil getAllCustomers() {
        return new ResponseUtil(200, "Success", customerService.getAllCustomers());
    }
}
