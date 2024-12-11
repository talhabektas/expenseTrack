package com.example.demo.controller;

import com.example.demo.dto.ExpenseSummaryDTO;
import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ExpenseController {

    private final ExpenseService expenseService;
    private final EmployeeService employeeService;

    @GetMapping("/expenses/summary/{userId}")
    public ResponseEntity<ExpenseSummaryDTO> getExpenseSummary(@PathVariable Integer userId) {
        ExpenseSummaryDTO summary = expenseService.getExpenseSummaryForUser(userId);
        return ResponseEntity.ok(summary);
    }

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @GetMapping("/expenses/total/{userId}")
    public ResponseEntity<BigDecimal> getTotalExpenses(@PathVariable Integer userId) {
        BigDecimal total = expenseService.getTotalExpenseByUserId(userId);
        return ResponseEntity.ok(total);
    }
}