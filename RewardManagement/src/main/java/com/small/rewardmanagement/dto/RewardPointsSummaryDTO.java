package com.small.rewardmanagement.dto;


import lombok.Data;

import java.util.List;

@Data
public class RewardPointsSummaryDTO {
    private String month;
    private int year;
    private int totalPoints;
    private List<TransactionDTO> transactions;
}
