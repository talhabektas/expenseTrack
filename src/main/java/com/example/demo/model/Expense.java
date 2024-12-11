package com.example.demo.model;

import lombok.Data;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import java.math.BigDecimal;

@Data
@Table("user_expenses")
public class Expense {
    @PrimaryKey
    private ExpenseKey key;
    private String description;
    private String type;
    private BigDecimal payment;
}