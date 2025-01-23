package com.small.rewardmanagement.controller;

import com.small.rewardmanagement.dto.CustomerRewardDTO;
import com.small.rewardmanagement.service.RewardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rewards")
@RequiredArgsConstructor
public class RewardController {
    private final RewardService rewardService;

    @GetMapping("/{customerId}/{month}")
    public CustomerRewardDTO getMonthlyRewards(
            @PathVariable String customerId,
            @PathVariable String month) {
        return rewardService.getMonthlyRewards(customerId, month);
    }
}
