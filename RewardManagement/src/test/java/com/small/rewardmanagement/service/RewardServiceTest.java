package com.small.rewardmanagement.service;

import com.small.rewardmanagement.dto.CustomerRewardDTO;
import com.small.rewardmanagement.entity.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.Collections;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import com.small.rewardmanagement.repository.CustomerRepository;
import com.small.rewardmanagement.repository.TransactionRepository;

@SpringBootTest
@ExtendWith(SpringExtension.class)

public class RewardServiceTest {

    @InjectMocks
    private RewardService rewardService;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private TransactionRepository transactionRepository;

    @BeforeEach
    public void setUp() {
        Mockito.when(customerRepository.findById(anyString())).thenReturn(Optional.empty());
        Mockito.when(transactionRepository.findByCustomerCustomerIdAndTransactionDateBetween(anyString(), any(), any())).thenReturn(Collections.emptyList());
    }

    @Test
    public void testInjections() {
        assertNotNull(customerRepository);
        assertNotNull(transactionRepository);
        assertNotNull(rewardService);
    }

    @Test
    public void testGetMonthlyRewards_CustomerNotFound() {
        String customerId = "invalidCustomerId";
        String month = "January";

        assertThrows(IllegalArgumentException.class, () -> rewardService.getMonthlyRewards(customerId, month));
    }

    @Test
    public void testGetMonthlyRewards_InvalidMonth() {
        String customerId = "customer";
        String invalidMonth = "InvalidMonth";

        Customer customer = new Customer(customerId, "John Doe");
        Mockito.when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));

        assertThrows(IllegalArgumentException.class, () -> rewardService.getMonthlyRewards(customerId, invalidMonth));
    }

    @Test
    public void testGetMonthlyRewards_NoTransactions() {
        String customerId = "customer";
        String month = "January";

        Customer customer = new Customer(customerId, "John Doe");
        Mockito.when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));

        CustomerRewardDTO rewardDTO = rewardService.getMonthlyRewards(customerId, month);

        assertEquals(customerId, rewardDTO.getCustomerId());
        assertEquals(customer.getCustomerName(), rewardDTO.getCustomerName());
        assertEquals(Collections.emptyList(), rewardDTO.getRewardPointsSummary());
    }

}