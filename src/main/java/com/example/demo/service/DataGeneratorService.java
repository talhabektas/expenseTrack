package com.example.demo.service;

import com.example.demo.model.Expense;
import com.example.demo.model.ExpenseKey;
import com.example.demo.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class DataGeneratorService {

    private final KafkaTemplate<String, Expense> kafkaTemplate;
    private final EmployeeRepository employeeRepository;
    private final Random random = new Random();

    private static final String TOPIC = "expense-topic";

    private static final String[] EXPENSE_TYPES = {
            "FOOD", "TRANSPORT", "OFFICE", "TRAVEL", "ENTERTAINMENT"
    };

    private static final String[] DESCRIPTIONS = {
            "Lunch at Restaurant", "Taxi Fare", "Office Supplies",
            "Flight Ticket", "Team Building", "Client Meeting",
            "Coffee Break", "Train Ticket", "Hotel Stay"
    };

    @Scheduled(fixedRate = 1000) // Her saniye çalışır
    public void generateAndSendExpense() {
        Expense expense = generateRandomExpense();
        String userId = expense.getKey().getUserId().toString();

        try {
            kafkaTemplate.send(TOPIC, userId, expense);
            log.info("Expense generated and sent to Kafka - UserId: {}, Amount: {}",
                    userId, expense.getPayment());
        } catch (Exception e) {
            log.error("Error sending expense to Kafka: {}", e.getMessage());
        }
    }

    private Expense generateRandomExpense() {
        Expense expense = new Expense();
        ExpenseKey key = new ExpenseKey();


        int randomEmployeeId = 7369 + random.nextInt(14);

        key.setUserId(randomEmployeeId);
        key.setDateTime(LocalDateTime.now());

        expense.setKey(key);
        expense.setType(EXPENSE_TYPES[random.nextInt(EXPENSE_TYPES.length)]);
        expense.setDescription(DESCRIPTIONS[random.nextInt(DESCRIPTIONS.length)]);


        BigDecimal payment = BigDecimal.valueOf(10 + random.nextDouble() * 990)
                .setScale(2, RoundingMode.HALF_UP);
        expense.setPayment(payment);

        return expense;
    }
}