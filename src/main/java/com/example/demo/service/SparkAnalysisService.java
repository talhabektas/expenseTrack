package com.example.demo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static org.apache.spark.sql.functions.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class SparkAnalysisService {

    private final SparkSession sparkSession;


    public Dataset<Row> getDepartmentExpenses() {
        Map<String, String> options = new HashMap<>();
        options.put("keyspace", "expense_tracking");
        options.put("table", "user_expenses");

        Dataset<Row> expenses = sparkSession.read()
                .format("org.apache.spark.sql.cassandra")
                .options(options)
                .load();

        return expenses
                .join(getEmployeeDataset(), col("key.userId").equalTo(col("empno")))
                .groupBy("deptno")
                .agg(
                        sum("payment").as("total_expense"),
                        avg("payment").as("avg_expense"),
                        count("*").as("transaction_count")
                )
                .orderBy(col("total_expense").desc());
    }


    public Dataset<Row> getTimeBasedAnalysis() {
        Map<String, String> options = new HashMap<>();
        options.put("keyspace", "expense_tracking");
        options.put("table", "user_expenses");

        Dataset<Row> expenses = sparkSession.read()
                .format("org.apache.spark.sql.cassandra")
                .options(options)
                .load();

        return expenses
                .withColumn("date", date_format(col("key.dateTime"), "yyyy-MM-dd"))
                .groupBy("date")
                .agg(
                        sum("payment").as("daily_total"),
                        count("*").as("transaction_count"),
                        avg("payment").as("avg_expense")
                )
                .orderBy("date");
    }


    public Dataset<Row> getExpenseTypeAnalysis() {
        Map<String, String> options = new HashMap<>();
        options.put("keyspace", "expense_tracking");
        options.put("table", "user_expenses");

        Dataset<Row> expenses = sparkSession.read()
                .format("org.apache.spark.sql.cassandra")
                .options(options)
                .load();

        return expenses
                .groupBy("type")
                .agg(
                        sum("payment").as("total_expense"),
                        count("*").as("transaction_count"),
                        avg("payment").as("avg_expense")
                )
                .orderBy(col("total_expense").desc());
    }


    public Dataset<Row> getTopSpenders() {
        Map<String, String> options = new HashMap<>();
        options.put("keyspace", "expense_tracking");
        options.put("table", "user_expenses");

        Dataset<Row> expenses = sparkSession.read()
                .format("org.apache.spark.sql.cassandra")
                .options(options)
                .load();

        return expenses
                .groupBy(col("key.userId"))
                .agg(sum("payment").as("total_spent"))
                .orderBy(col("total_spent").desc())
                .limit(5);
    }

    private Dataset<Row> getEmployeeDataset() {
        return sparkSession.read()
                .format("jdbc")
                .option("url", "jdbc:mysql://localhost:3306/emp")
                .option("dbtable", "employees")
                .option("user", "root")
                .option("password", "your_password")
                .load();
    }
}