package com.example.demo.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class ExpenseSummaryDTO {
    private Integer userId;
    private String userName;
    private BigDecimal totalExpense;
    private String departmentName;
}