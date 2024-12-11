// EmployeeDTO.java
package com.example.demo.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class EmployeeDTO {
    private Integer empno;
    private String ename;
    private String job;
    private Integer mgr;
    private String hiredate;
    private BigDecimal sal;
    private BigDecimal comm;
    private Integer deptno;
    private String img;
}