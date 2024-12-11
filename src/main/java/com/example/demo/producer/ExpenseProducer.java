package com.example.demo.producer;

import com.example.demo.model.Expense;
import com.example.demo.model.ExpenseKey;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class ExpenseProducer {

    private static final String TOPIC = "expense-topic";
    private final KafkaTemplate<String, Expense> kafkaTemplate;
    private final Random random = new Random();

    @Scheduled(fixedRate = 1000)
    public void generateAndSendExpense() {
        Expense expense = createRandomExpense();
        kafkaTemplate.send(TOPIC,
                expense.getKey().getUserId().toString(),
                expense);
    }

    private Expense createRandomExpense() {
        ExpenseKey key = new ExpenseKey();
        key.setUserId(random.nextInt(14) + 1);
        key.setDateTime(LocalDateTime.now());

        Expense expense = new Expense();
        expense.setKey(key);
        expense.setDescription(getRandomDescription());
        expense.setType(getRandomType());
        expense.setPayment(getRandomAmount());

        return expense;
    }

    private String getRandomDescription() {
        String[] descriptions = {
                "Lunch", "Dinner", "Coffee", "Transport",
                "Shopping", "Books", "Electronics"
        };
        return descriptions[random.nextInt(descriptions.length)];
    }

    private String getRandomType() {
        String[] types = {
                "FOOD", "TRANSPORT", "SHOPPING",
                "ENTERTAINMENT", "EDUCATION"
        };
        return types[random.nextInt(types.length)];
    }

    private BigDecimal getRandomAmount() {
        return BigDecimal.valueOf(random.nextDouble() * 1000)
                .setScale(2, RoundingMode.HALF_UP);
    }
}