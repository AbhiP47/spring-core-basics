package com.abhinav.jpa.controller;

import com.abhinav.jpa.model.Department;
import com.abhinav.jpa.service.DepartmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {
    DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping
    public ResponseEntity<String> createDepartment(
            @RequestBody Department department) {

        departmentService.createDepartment(department);
        return ResponseEntity.ok("DONE");
    }

    @PostMapping("/withStudent")
    public ResponseEntity<String> createDepartment(
            @RequestBody Department department,
            @RequestParam String studentName
    ) {

        departmentService.createDepartment(department, studentName);
        return ResponseEntity.ok("DONE");
    }
}