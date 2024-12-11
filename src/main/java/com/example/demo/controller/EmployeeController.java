package com.example.demo.controller;

import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @GetMapping("/{empno}")
    public ResponseEntity<Employee> getEmployee(@PathVariable Integer empno) {
        return ResponseEntity.ok(employeeService.getEmployeeById(empno));
    }

    @PostMapping("/{empno}/image")
    public ResponseEntity<Employee> updateEmployeeImage(
            @PathVariable Integer empno,
            @RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(employeeService.updateEmployeeImage(empno, file));
    }
}