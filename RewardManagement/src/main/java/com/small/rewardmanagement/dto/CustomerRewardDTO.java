package com.small.rewardmanagement.dto;

import lombok.Data;
import java.util.List;


@Data
public class CustomerRewardDTO {
    private String customerId;
    private String customerName;
    private List<RewardPointsSummaryDTO> rewardPointsSummary;
}
