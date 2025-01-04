package com.fapah.crud.service;

import com.fapah.crud.exception.EmptyResultException;
import com.fapah.crud.entity.Department;
import com.fapah.crud.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Override
    public List<Department> findAll() {
        List<Department> departments = departmentRepository.findAll();
        if (departments.isEmpty()) {
            throw new EmptyResultException("Department list is empty");
        }
        log.info("Found {} departments", departments.size());
        return departments;
    }

    @Override
    public Department addDepartment(Department department) {
        log.info("Adding department {}", department);
        return departmentRepository.saveAndFlush(department);
    }
}
