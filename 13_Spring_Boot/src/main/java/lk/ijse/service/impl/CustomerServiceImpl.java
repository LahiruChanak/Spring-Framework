package lk.ijse.service.impl;

import lk.ijse.dto.CustomerDTO;
import lk.ijse.entity.Customer;
import lk.ijse.repository.CustomerRepo;
import lk.ijse.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private ModelMapper modelMapper; // ModelMapper

    @Override
    public boolean saveCustomer(CustomerDTO customerDTO) {
//        Customer customer = new Customer(
//                customerDTO.getId(),
//                customerDTO.getName(),
//                customerDTO.getAddress());
        if (customerRepo.existsById(customerDTO.getId())) {
            return false;
        }
        customerRepo.save(modelMapper.map(customerDTO, Customer.class));
        return true;
    }

    @Override
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

    @Override
    public boolean updateCustomer(CustomerDTO customerDTO) {
        if (customerRepo.existsById(customerDTO.getId())) {
//            Customer customer = new Customer(
//                    customerDTO.getId(),
//                    customerDTO.getName(),
//                    customerDTO.getAddress());
            customerRepo.save(modelMapper.map(customerDTO, Customer.class));
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteCustomer(int id) {
        if (customerRepo.existsById(id)) {
            customerRepo.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
//        List<Customer> customers = customerRepo.findAll();
//        List<CustomerDTO> customerDTOs = new ArrayList<>();
//
//        for (Customer customer : customers) {
//            customerDTOs.add(new CustomerDTO(
//                    customer.getId(),
//                    customer.getName(),
//                    customer.getAddress()));
//        }
        return modelMapper.map(customerRepo.findAll(), new TypeToken<List<CustomerDTO>>() {
        }.getType());

    }
}
