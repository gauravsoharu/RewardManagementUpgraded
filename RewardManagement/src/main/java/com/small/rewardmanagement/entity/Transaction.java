package com.small.rewardmanagement.entity;

import lombok.Data;
import jakarta.persistence.*;


import java.time.LocalDate;

@Entity
@Data
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;
    private LocalDate transactionDate;
    private Double transactionAmount;
    private Integer pointsEarned;
    private String transactionDescription;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
