package com.example.demo.repository;

import com.example.demo.model.Expense;
import com.example.demo.model.ExpenseKey;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepository extends CassandraRepository<Expense, ExpenseKey> {
}