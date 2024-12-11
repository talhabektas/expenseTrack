package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "employees")
public class Employee {
    @Id
    private Integer empno;
    private String ename;
    private String job;
    private Integer mgr;

    @Column(name = "hiredate")
    private LocalDate hireDate;
    private BigDecimal sal;
    private BigDecimal comm;
    private Integer deptno;
    private String img;
}