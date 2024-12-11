package com.example.demo.service;

import com.example.demo.dto.ExpenseSummaryDTO;
import com.example.demo.model.Department;
import com.example.demo.model.Employee;
import com.example.demo.model.Expense;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ExpenseService {
    private final ExpenseRepository expenseRepository;
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    public ExpenseSummaryDTO getExpenseSummaryForUser(Integer userId) {
        Employee employee = employeeRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        Department department = departmentRepository.findById(employee.getDeptno())
                .orElseThrow(() -> new RuntimeException("Department not found"));

        BigDecimal totalExpense = getTotalExpenseByUserId(userId);

        ExpenseSummaryDTO summary = new ExpenseSummaryDTO();
        summary.setUserId(userId);
        summary.setUserName(employee.getEname());
        summary.setTotalExpense(totalExpense);
        summary.setDepartmentName(department.getDname());

        return summary;
    }

    public BigDecimal getTotalExpenseByUserId(Integer userId) {
        return expenseRepository.findAll().stream()
                .filter(expense -> expense.getKey().getUserId().equals(userId))
                .map(Expense::getPayment)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}