package com.example.demo.controller;

import com.example.demo.service.SparkAnalysisService;
import lombok.RequiredArgsConstructor;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/analytics")
@RequiredArgsConstructor
public class AnalyticsController {

    private final SparkAnalysisService sparkAnalysisService;

    @GetMapping("/department-expenses")
    public List<String> getDepartmentExpenses() {
        Dataset<Row> result = sparkAnalysisService.getDepartmentExpenses();
        return result.toJSON().collectAsList();
    }

    @GetMapping("/time-analysis")
    public List<String> getTimeAnalysis() {
        Dataset<Row> result = sparkAnalysisService.getTimeBasedAnalysis();
        return result.toJSON().collectAsList();
    }

    @GetMapping("/expense-types")
    public List<String> getExpenseTypeAnalysis() {
        Dataset<Row> result = sparkAnalysisService.getExpenseTypeAnalysis();
        return result.toJSON().collectAsList();
    }

    @GetMapping("/top-spenders")
    public List<String> getTopSpenders() {
        Dataset<Row> result = sparkAnalysisService.getTopSpenders();
        return result.toJSON().collectAsList();
    }
}