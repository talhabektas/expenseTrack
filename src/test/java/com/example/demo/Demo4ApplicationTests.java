package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = {
        "spring.kafka.bootstrap-servers=localhost:9092",
        "spring.data.cassandra.local-datacenter=datacenter1",
        "spring.data.cassandra.keyspace-name=expense_tracking"
})
class Demo4ApplicationTests {

    @Test
    void contextLoads() {
    }
}