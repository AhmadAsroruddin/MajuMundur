package com.ari.majumundur.Service.impl;

import com.ari.majumundur.Models.Entities.Customer;
import com.ari.majumundur.Repository.CustomerRepository;
import com.ari.majumundur.Service.CustomerService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    @Override
    public Customer getById(String id) {
        return customerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No Customer Found"));
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }


    @Override
    public List<Customer> findCustomerByStore(String merchantId) {
        return null;
    }

}
