package com.fapah.crud.controller;

import com.fapah.crud.exception.NullParameterException;
import com.fapah.crud.entity.Department;
import com.fapah.crud.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.NestedRuntimeException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/department")
@RequiredArgsConstructor
@Slf4j
public class DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping("/")
    public ResponseEntity<List<Department>> getAllDepartments() {
        log.info("Getting all departments");
        return ResponseEntity.ok(departmentService.findAll());
    }

    @PostMapping("/add")
    public ResponseEntity<Department> addDepartment(@RequestBody(required = true) Department department) {
        try {
            log.info("Adding department {}", department);
            return ResponseEntity.ok(departmentService.addDepartment(department));
        } catch (NestedRuntimeException e) {
            log.warn("Warn while adding department {}", department, e);
            throw new NullParameterException("One of the required parameters is null");
        }
    }
}
