package lk.ijse.service;

import lk.ijse.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {

    public boolean saveCustomer(CustomerDTO customerDTO);
    public boolean updateCustomer(CustomerDTO customerDTO);
    public boolean deleteCustomer(int id);
    public CustomerDTO getCustomerById(int id);
    public List<CustomerDTO> getAllCustomers();

}
