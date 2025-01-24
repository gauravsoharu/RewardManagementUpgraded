package com.small.rewardmanagement.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.small.rewardmanagement.dto.CustomerRewardDTO;
import com.small.rewardmanagement.service.RewardService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.http.MediaType;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(MockitoJUnitRunner.class)
public class RewardControllerTest {

    private MockMvc mockMvc;

    @Mock
    private RewardService rewardService;

    @InjectMocks
    private RewardController rewardController;

    private ObjectMapper objectMapper;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(rewardController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testGetMonthlyRewards() throws Exception {

        String customerId = "12345";
        String month = "January";
        CustomerRewardDTO customerRewardDTO = new CustomerRewardDTO();
        customerRewardDTO.setCustomerId(customerId);
        customerRewardDTO.setCustomerName("John Doe");
        customerRewardDTO.setRewardPointsSummary(null);  // You can add more data as required


        when(rewardService.getMonthlyRewards(customerId, month)).thenReturn(customerRewardDTO);


        mockMvc.perform(get("/api/rewards/{customerId}/{month}", customerId, month)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())  // Verify HTTP status is 200 OK
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(customerRewardDTO)));  // Verify the JSON response
    }
}
