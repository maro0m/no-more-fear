package com.CustomerInfo.CustomerInfo.repository;

import com.CustomerInfo.CustomerInfo.modal.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{
    Optional<Customer> deleteCustomerById(Long id);
    boolean existsByEmail(String email);
    Optional<Customer> findByEmail(String email);
    List<Customer> id(Long id);
}

