package com.fapah.crud.service;

import com.fapah.crud.exception.EmptyResultException;
import com.fapah.crud.exception.NoSuchDataException;
import com.fapah.crud.entity.Department;
import com.fapah.crud.entity.Employee;
import com.fapah.crud.repository.DepartmentRepository;
import com.fapah.crud.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final DepartmentRepository departmentRepository;

    @Override
    public List<Employee> findAll() {
        try {
            List<Employee> employees = employeeRepository.findAll();
            if (employees.isEmpty()) {
                log.info("No department found");
                return Collections.emptyList();
            }
            log.info("Found {} employees", employees.size());
            return employees;
        } catch (RuntimeException e) {
            throw new RuntimeException("Unexpected Error in findAll in EmployeeServiceImpl", e);
        }
    }

    @Transactional
    @Override
    public Employee addEmployee(Employee employee, long departmentId) {
        try {
            Optional<Department> department = departmentRepository.findById(departmentId);
            log.info("Found department {} with id {}",department , departmentId);
            department.get().getEmployees().add(employee);
            employee.setDepartment(department.get());
            log.info("Adding employee {}", employee);
            departmentRepository.save(department.get());
            return employee;
        } catch (NoSuchElementException e) {
            log.warn("No department found with id {}", departmentId);
            throw new NoSuchDataException("Department not found");
        } catch (RuntimeException e) {
            log.warn("Unexpected exception while adding employee {} with departmentID {}", employee, departmentId, e);
            throw new RuntimeException("Unexpected Error adding employee", e);
        }
    }
}
