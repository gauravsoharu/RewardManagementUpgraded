package com.small.rewardmanagement.controller;

import com.small.rewardmanagement.dto.CustomerRewardDTO;
import com.small.rewardmanagement.service.RewardService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RewardControllerTest {

    @Mock
    private RewardService rewardService;

    @InjectMocks
    private RewardController rewardController;

    @Test
    void testGetMonthlyRewards() {
        MockitoAnnotations.openMocks(this);


        String customerId = "C12345";
        String month = "January";

        CustomerRewardDTO mockResponse = new CustomerRewardDTO();
        mockResponse.setCustomerId(customerId);
        mockResponse.setCustomerName("ABC");

        when(rewardService.getMonthlyRewards(customerId, month)).thenReturn(mockResponse);


        CustomerRewardDTO result = rewardController.getMonthlyRewards(customerId, month);


        assertNotNull(result);
        assertEquals(customerId, result.getCustomerId());
        assertEquals("ABC", result.getCustomerName());


        verify(rewardService, times(1)).getMonthlyRewards(customerId, month);
    }

}
