package com.CustomerInfo.CustomerInfo.service;

import com.CustomerInfo.CustomerInfo.modal.Customer;
import com.CustomerInfo.CustomerInfo.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public ArrayList<Customer> findAllcustomers() {
        return (ArrayList<Customer>) customerRepository.findAll();
    }

    @Override
    public Customer findAllCustomerByID(long id) {  // Changed to primitive long
        Optional<Customer> opt = customerRepository.findById(id);
        return opt.orElse(null);
    }


    @Override
    public void addCustomer() {

        List<Customer> customers = Arrays.asList(
                Customer.builder()
                        .name("Shubham")
                        .city("Lucknow")
                        .balance(new BigDecimal("1500.50"))  // Use BigDecimal
                        .age(25)
                        .password("Password123")
                        .email("shubham@example.com")
                        .gender("Male")
                        .creditCard("1234567890123456")
                        .birthDate(LocalDate.of(1998, 5, 15))
                        .membershipExpiry(LocalDate.now().plusYears(1))
                        .agreedToTerms(true)
                        .accountSuspended(false)
                        .discountRate(new BigDecimal("15.500"))  // Use BigDecimal
                        .members(List.of("Family Member 1", "Family Member 2"))
                        .build(),

                Customer.builder()
                        .name("Puneet")
                        .city("Delhi")
                        .balance(new BigDecimal("2000.75"))
                        .age(30)
                        .password("SecurePass456")
                        .email("puneet@example.com")
                        .gender("Male")
                        .creditCard("9876543210987654")
                        .birthDate(LocalDate.of(1993, 8, 20))
                        .membershipExpiry(LocalDate.now().plusMonths(6))
                        .agreedToTerms(true)
                        .accountSuspended(false)
                        .discountRate(new BigDecimal("10.250"))
                        .members(List.of("Spouse", "Child"))
                        .build()
        );

        customerRepository.saveAll(customers);
    }    @Override
    public void deleteAllData() {
        customerRepository.deleteAll();
    }




    @Override
    public Customer createCustomer(Customer customer) {
        // Check if email already exists
        if (customerRepository.existsByEmail(customer.getEmail())) {
            throw new RuntimeException("Email already exists: " + customer.getEmail());
        }
        return customerRepository.save(customer);
    }




    @Override
    public Customer updateCustomer(long id, Customer customer) {
        // Check if customer exists
        Optional<Customer> existingCustomerOpt = customerRepository.findById(id);
        if (existingCustomerOpt.isPresent()) {
            // Set the ID to ensure we're updating the correct customer
            customer.setId(id);
            return customerRepository.save(customer);
        } else {
            throw new RuntimeException("Customer not found with id: " + id);
        }
    }

    @Override
    public void deleteCustomerById(long id) {
        // Check if customer exists before deleting
        if (customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
        } else {
            throw new RuntimeException("Customer not found with id: " + id);
        }
    }

}