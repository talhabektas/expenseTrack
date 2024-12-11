package com.example.demo.controller;

import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequiredArgsConstructor
public class WebController {

    private final EmployeeService employeeService;
    private final ExpenseService expenseService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("employees", employeeService.getAllEmployees());
        return "index";
    }

    @GetMapping("/employee/{empno}")
    public String employeeDetail(@PathVariable Integer empno, Model model) {
        Employee employee = employeeService.getEmployeeById(empno);
        model.addAttribute("employee", employee);
        model.addAttribute("totalExpenses", expenseService.getTotalExpenseByUserId(empno));
        return "employee-detail";
    }

    @PostMapping("/employee/{empno}/image")
    public String uploadImage(@PathVariable Integer empno,
                              @RequestParam("file") MultipartFile file) {
        employeeService.updateEmployeeImage(empno, file);
        return "redirect:/employee/" + empno;
    }
}