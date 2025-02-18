package lk.ijse.service;

import lk.ijse.dto.CustomerDTO;
import lk.ijse.entity.Customer;
import lk.ijse.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    public boolean save(CustomerDTO customerDTO) {
        Customer customer = new Customer(
                customerDTO.getId(),
                customerDTO.getName(),
                customerDTO.getAddress());
        customerRepo.save(customer);
        return true;
    }

    public CustomerDTO getCustomerById(int id) {
        Optional<Customer> customerOptional = customerRepo.findById(id);
        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            return new CustomerDTO(
                    customer.getId(),
                    customer.getName(),
                    customer.getAddress());
        }
        return null;
    }

    public boolean updateCustomer(int id, CustomerDTO customerDTO) {
        Optional<Customer> customerOptional = customerRepo.findById(id);
        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            customer.setName(customerDTO.getName());
            customer.setAddress(customerDTO.getAddress());
            customerRepo.save(customer);
            return true;
        }
        return false;
    }

    public boolean deleteCustomer(int id) {
        if (customerRepo.existsById(id)) {
            customerRepo.deleteById(id);
            return true;
        }
        return false;
    }

    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = customerRepo.findAll();
        List<CustomerDTO> customerDTOs = new ArrayList<>();
        for (Customer customer : customers) {
            customerDTOs.add(new CustomerDTO(
                    customer.getId(),
                    customer.getName(),
                    customer.getAddress()));
        }
        return customerDTOs;
    }
}
