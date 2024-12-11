package com.example.demo.consumer;

import com.example.demo.model.Expense;
import com.example.demo.repository.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ExpenseConsumer {

    private final ExpenseRepository expenseRepository;

    @KafkaListener(topics = "expense-topic", groupId = "expense-group")
    public void consumeExpense(Expense expense) {
        try {
            expenseRepository.save(expense);
            log.info("Expense saved for user: {}, amount: {}",
                    expense.getKey().getUserId(),
                    expense.getPayment());
        } catch (Exception e) {
            log.error("Error saving expense: {}", e.getMessage());
        }
    }
}