package com.example.demo.model;

import lombok.Data;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import java.io.Serializable;
import java.time.LocalDateTime;
@Data
@PrimaryKeyClass
public class ExpenseKey implements Serializable {
    @PrimaryKeyColumn(name = "user_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private Integer userId;

    @PrimaryKeyColumn(name = "date_time", ordinal = 1, type = PrimaryKeyType.CLUSTERED)
    private LocalDateTime dateTime;


}