package com.example.demo.service;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final S3Service s3Service;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Integer empno) {
        return employeeRepository.findById(empno)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + empno));
    }

    public Employee updateEmployeeImage(Integer empno, MultipartFile file) {
        Employee employee = getEmployeeById(empno);
        try {
            String fileName = "employee-" + empno + "-" + file.getOriginalFilename();
            String fileUrl = s3Service.uploadFile(fileName, file);
            employee.setImg(fileName);
            return employeeRepository.save(employee);
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload image: " + e.getMessage());
        }
    }
}