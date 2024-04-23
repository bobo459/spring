package dw.wholesale_company.service;


import dw.wholesale_company.model.Customer;
import dw.wholesale_company.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    CustomerRepository customerRepository;
@Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    public Customer saveCustomer(Customer customer){
    customerRepository.save(customer);
    return customer;
    }
    public List<Customer> getCustomerAll() {return customerRepository.findAll();}


}
