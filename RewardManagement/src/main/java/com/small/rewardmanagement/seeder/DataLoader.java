package com.small.rewardmanagement.seeder;

import com.small.rewardmanagement.entity.Customer;
import com.small.rewardmanagement.entity.Transaction;
import com.small.rewardmanagement.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final CustomerRepository customerRepository;

    @Override
    public void run(String... args) {
        Customer customer = new Customer();
        customer.setCustomerId("C12345");
        customer.setCustomerName("ABC");

        Transaction t1 = new Transaction();
        t1.setTransactionDate(LocalDate.of(2025, 1, 5));
        t1.setTransactionAmount(500.0);
        t1.setPointsEarned(100);
        t1.setTransactionDescription("Purchase at XYZ Store");
        t1.setCustomer(customer);

        Transaction t2 = new Transaction();
        t2.setTransactionDate(LocalDate.of(2025, 1, 25));
        t2.setTransactionAmount(150.0);
        t2.setPointsEarned(70);
        t2.setTransactionDescription("Dining at DEF Restaurant");
        t2.setCustomer(customer);

        customer.setTransactions(List.of(t1, t2));

        customerRepository.save(customer);
    }
}
