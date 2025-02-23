package lk.ijse.service;

import lk.ijse.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {

    public void saveCustomer(CustomerDTO customerDTO);

    public void updateCustomer(CustomerDTO customerDTO);

    public void deleteCustomer(int id);

    public CustomerDTO getCustomerById(int id);

    public List<CustomerDTO> getAllCustomers();

}
