package com.ari.majumundur.Service;

import com.ari.majumundur.Models.Entities.Customer;

import java.util.List;

public interface CustomerService {
    Customer getById(String id);

    Customer save(Customer customer);

    List<Customer> findCustomerByStore(String merchantId);
}
