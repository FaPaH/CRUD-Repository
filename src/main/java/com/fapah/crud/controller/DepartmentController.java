package com.fapah.crud.controller;

import com.fapah.crud.exception.NullParameterException;
import com.fapah.crud.entity.Department;
import com.fapah.crud.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/department")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping("/")
    public ResponseEntity<List<Department>> getAllDepartments() {
        return ResponseEntity.ok(departmentService.findAll());
    }

    @PostMapping("/add")
    public ResponseEntity<Department> addDepartment(@RequestBody(required = true) Department department) {
        try {
            return ResponseEntity.ok(departmentService.addDepartment(department));
        } catch (RuntimeException e) {
            throw new NullParameterException("One of the required parameters is null");
        }
    }
}
