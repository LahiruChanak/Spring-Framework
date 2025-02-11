package lk.ijse.service;

import lk.ijse.dto.CustomerDTO;
import lk.ijse.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    public boolean saveCustomer(CustomerDTO dto) {
        System.out.println("Service Layer: " + dto.getName());

        return true;
    }
}
