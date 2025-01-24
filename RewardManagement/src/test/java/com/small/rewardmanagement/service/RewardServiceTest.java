package com.small.rewardmanagement.service;

import com.small.rewardmanagement.dto.CustomerRewardDTO;
import com.small.rewardmanagement.dto.RewardPointsSummaryDTO;
import com.small.rewardmanagement.entity.Customer;
import com.small.rewardmanagement.entity.Transaction;
import com.small.rewardmanagement.repository.CustomerRepository;
import com.small.rewardmanagement.repository.TransactionRepository;
import com.small.rewardmanagement.utility.DateUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class RewardServiceTest {

    @InjectMocks
    private RewardService rewardService;

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private CustomerRepository customerRepository;

    private Customer customer;
    private List<Transaction> transactions;

    @Before
    public void setUp() {
        customer = new Customer();
        customer.setCustomerId("12345");
        customer.setCustomerName("John Doe");

        Transaction transaction1 = new Transaction();
        transaction1.setTransactionId(1L);
        transaction1.setTransactionDate(LocalDate.of(2025, 1, 5));
        transaction1.setPointsEarned(100);
        transaction1.setTransactionAmount(500.0);  // Changed to double
        transaction1.setTransactionDescription("Purchase 1");

        Transaction transaction2 = new Transaction();
        transaction2.setTransactionId(2L);  // Changed to long
        transaction2.setTransactionDate(LocalDate.of(2025, 1, 15));
        transaction2.setPointsEarned(150);
        transaction2.setTransactionAmount(800.0);  // Changed to double
        transaction2.setTransactionDescription("Purchase 2");

        transactions = Arrays.asList(transaction1, transaction2);
    }

    @Test
    public void testGetMonthlyRewards() {

        String customerId = "12345";
        String month = "January";

        when(customerRepository.findById(customerId)).thenReturn(java.util.Optional.of(customer));
        when(transactionRepository.findByCustomerCustomerIdAndTransactionDateBetween(
                customerId,
                DateUtil.getDateRangeForMonth(month).startDate(),
                DateUtil.getDateRangeForMonth(month).endDate()
        )).thenReturn(transactions);


        CustomerRewardDTO result = rewardService.getMonthlyRewards(customerId, month);


        assertNotNull(result);
        assertEquals("12345", result.getCustomerId());
        assertEquals("John Doe", result.getCustomerName());
        assertEquals(1, result.getRewardPointsSummary().size()); // One month summary should be there
        RewardPointsSummaryDTO summary = result.getRewardPointsSummary().get(0);
        assertEquals("january", summary.getMonth().toLowerCase());
        assertEquals(2, summary.getTransactions().size()); // Two transactions should be there
        assertEquals(Long.valueOf(1L), summary.getTransactions().get(0).getTransactionId());
        assertEquals(Long.valueOf(2L), summary.getTransactions().get(1).getTransactionId());


        assertEquals(500.0, summary.getTransactions().get(0).getTransactionAmount(), 0.001);
        assertEquals(800.0, summary.getTransactions().get(1).getTransactionAmount(), 0.001);
    }
}
