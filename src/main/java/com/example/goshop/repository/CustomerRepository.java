package com.example.goshop.repository;

import com.example.goshop.model.Customer;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    public Customer findByEmailId(String emailId);
}
