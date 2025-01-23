package com.small.rewardmanagement.dto;


import lombok.Data;

@Data
public class TransactionDTO {
    private Long transactionId;
    private String transactionDate;
    private Double transactionAmount;
    private Integer pointsEarned;
    private String transactionDescription;
}
