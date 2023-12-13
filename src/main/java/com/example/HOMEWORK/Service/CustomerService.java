package com.example.HOMEWORK.Service;

import com.example.HOMEWORK.Entity.Customer;
import com.example.HOMEWORK.Repository.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder PasswordEncoder;
    public String saveCustomer(Customer customer) {

        customer.setPassword(PasswordEncoder.encode(customer.getPassword()));
        Customer savedcustomer =customerRepository.save(customer);
        return savedcustomer.getId();
    }

    public void deleteCustomer(Customer customer) {
        customerRepository.delete(customer);
    }

    public Customer findCustomerById(String id) {
        Customer foundCustomer = customerRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return foundCustomer;

    }


    public List<Customer> findAllCustomer() {
        List<Customer> customerList = customerRepository.findAll();
        return customerList;
    }

}
