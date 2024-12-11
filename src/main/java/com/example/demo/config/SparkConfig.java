package com.example.demo.config;

import org.apache.spark.SparkConf;
import org.apache.spark.sql.SparkSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SparkConfig {

    @Bean
    public SparkSession sparkSession() {
        System.setProperty("hadoop.home.dir", System.getProperty("user.dir") + "/hadoop");

        SparkConf conf = new SparkConf()
                .setAppName("ExpenseTracking")
                .setMaster("local[*]")
                .set("spark.driver.host", "localhost")
                .set("spark.driver.bindAddress", "localhost")
                .set("spark.sql.shuffle.partitions", "1")
                .set("spark.default.parallelism", "1")
                .set("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
                .set("spark.jars.packages", "com.datastax.spark:spark-cassandra-connector_2.12:3.5.0")
                .set("spark.cassandra.connection.host", "localhost")
                .set("spark.cassandra.connection.port", "9042")
                .set("spark.cassandra.auth.username", "")
                .set("spark.cassandra.auth.password", "");

        return SparkSession.builder()
                .config(conf)
                .getOrCreate();
    }
}