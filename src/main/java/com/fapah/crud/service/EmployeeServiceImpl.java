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

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final DepartmentRepository departmentRepository;

    @Override
    public List<Employee> findAll() {
        List<Employee> employees = employeeRepository.findAll();
        if (employees.isEmpty()) {
            throw new EmptyResultException("Department list is empty");
        }
        log.info("Found {} employees", employees.size());
        return employees;
    }

    @Transactional
    @Override
    public Employee addEmployee(Employee employee, long departmentId) {
            Optional<Department> department = departmentRepository.findById(departmentId);
            if (department.isPresent()) {
                department.get().getEmployees().add(employee);
                employee.setDepartment(department.get());
                departmentRepository.save(department.get());
            } else {
                throw new NoSuchDataException("Department not found");
            }
            log.info("Added employee {}", employee);
            return employee;
    }
}
