package com.CustomerInfo.CustomerInfo.service;

import com.CustomerInfo.CustomerInfo.modal.Customer;


import java.util.ArrayList;

public interface CustomerService {
    ArrayList<Customer> findAllcustomers();
    Customer findAllCustomerByID(long id);  // Changed to primitive long
    void addCustomer();
    Customer updateCustomer(long id, Customer customer);
    void deleteCustomerById(long id);  // Change return type to void

    void deleteAllData();
    Customer createCustomer(Customer customer);
}