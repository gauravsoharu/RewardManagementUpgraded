package com.small.rewardmanagement.service;

import com.small.rewardmanagement.dto.CustomerRewardDTO;
import com.small.rewardmanagement.dto.RewardPointsSummaryDTO;
import com.small.rewardmanagement.dto.TransactionDTO;
import com.small.rewardmanagement.entity.Customer;
import com.small.rewardmanagement.entity.Transaction;
import com.small.rewardmanagement.exception.CustomerNotFoundException;
import com.small.rewardmanagement.exception.RewardProcessingException;
import com.small.rewardmanagement.repository.CustomerRepository;
import com.small.rewardmanagement.repository.TransactionRepository;
import com.small.rewardmanagement.utility.DateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RewardService {

    private final TransactionRepository transactionRepository;
    private final CustomerRepository customerRepository;

    public CustomerRewardDTO getMonthlyRewards(String customerId, String month) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found: " + customerId));

        DateUtil.DateRange dateRange = DateUtil.getDateRangeForMonth(month);

        List<Transaction> transactions;
        try {
            transactions = transactionRepository.findByCustomerCustomerIdAndTransactionDateBetween(
                    customerId, dateRange.startDate(), dateRange.endDate()
            );
        } catch (Exception e) {
            throw new RewardProcessingException("Failed to fetch transactions for customer ID: " + customerId);
        }

        List<RewardPointsSummaryDTO> rewardPointsSummary = transactions.stream()
                .collect(Collectors.groupingBy(t -> t.getTransactionDate().getMonthValue()))
                .entrySet()
                .stream()
                .map(entry -> {
                    var dto = new RewardPointsSummaryDTO();
                    dto.setMonth(DateUtil.getMonthName(entry.getKey()));
                    dto.setYear(entry.getValue().get(0).getTransactionDate().getYear());
                    dto.setTotalPoints(entry.getValue().stream().mapToInt(Transaction::getPointsEarned).sum());
                    dto.setTransactions(entry.getValue().stream().map(this::mapToTransactionDTO).toList());
                    return dto;
                }).toList();

        CustomerRewardDTO customerRewardDTO = new CustomerRewardDTO();
        customerRewardDTO.setCustomerId(customer.getCustomerId());
        customerRewardDTO.setCustomerName(customer.getCustomerName());
        customerRewardDTO.setRewardPointsSummary(rewardPointsSummary);

        return customerRewardDTO;
    }

    private TransactionDTO mapToTransactionDTO(Transaction transaction) {
        var dto = new TransactionDTO();
        dto.setTransactionId(transaction.getTransactionId());
        dto.setTransactionDate(transaction.getTransactionDate().toString());
        dto.setTransactionAmount(transaction.getTransactionAmount());
        dto.setPointsEarned(transaction.getPointsEarned());
        dto.setTransactionDescription(transaction.getTransactionDescription());
        return dto;
    }
}
