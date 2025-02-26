package lk.ijse.controller;

import jakarta.servlet.http.HttpServletRequest;
import lk.ijse.entity.Customer;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "customer")
public class CustomerController {

    private List<Customer> customers = new ArrayList<Customer>(
    List.of(
        new Customer("C001", "Kamal"),
        new Customer("C002", "Nimal"),
        new Customer("C003", "Sunil")
    ));

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customers;
    }

    @PostMapping
    public String saveCustomer(@RequestBody Customer customer) {
        customers.add(customer);
        return "Customer Saved Successfully";
    }

    @GetMapping("token")
    public CsrfToken csrfToken(HttpServletRequest request) {
        return (CsrfToken) request.getAttribute("_csrf");
    }
}
