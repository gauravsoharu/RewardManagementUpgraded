package com.small.rewardmanagement.repository;



import com.small.rewardmanagement.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, String> {
}

