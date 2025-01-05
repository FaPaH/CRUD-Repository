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
            log.info("Find all employees in EmployeeServiceImpl");
            List<Employee> employees = employeeRepository.findAll();
            if (employees.isEmpty()) {
                log.debug("Throw EmptyResultException in findAll in EmployeeServiceImpl");
                throw new EmptyResultException("Employee list is empty");
            }
            log.info("Found {} employees", employees.size());
            return employees;
        } catch (RuntimeException e) {
            log.debug("Error in findAll in EmployeeServiceImpl", e);
            throw new RuntimeException("Unexpected Error in findAll in EmployeeServiceImpl", e);
        }
    }

    @Transactional
    @Override
    public Employee addEmployee(Employee employee, long departmentId) {
        try {
            log.info("Calling addEmployee in EmployeeServiceImpl with department id {}", departmentId);
            Optional<Department> department = departmentRepository.findById(departmentId);
            log.info("Found department {} with id {}",department , departmentId);
            department.get().getEmployees().add(employee);
            employee.setDepartment(department.get());
            log.info("Adding employee {}", employee);
            departmentRepository.save(department.get());
            return employee;
        } catch (NoSuchElementException e) {
            log.debug("No department found with id {}", departmentId);
            throw new NoSuchDataException("Department not found");
        } catch (RuntimeException e) {
            log.debug("Unexpected error in addEmployee in EmployeeServiceImpl", e);
            throw new RuntimeException("Unexpected Error adding employee", e);
        }
    }
}
