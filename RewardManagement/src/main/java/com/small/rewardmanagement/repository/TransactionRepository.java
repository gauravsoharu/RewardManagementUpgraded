package com.small.rewardmanagement.repository;


import com.small.rewardmanagement.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.time.LocalDate;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByCustomerCustomerIdAndTransactionDateBetween(
            String customerId, LocalDate startDate, LocalDate endDate);
}
