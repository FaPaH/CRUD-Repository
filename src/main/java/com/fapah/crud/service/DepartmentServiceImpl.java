package com.fapah.crud.service;

import com.fapah.crud.exception.EmptyResultException;
import com.fapah.crud.entity.Department;
import com.fapah.crud.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Override
    public List<Department> findAll() {
        try {
            List<Department> departments = departmentRepository.findAll();
            if (departments.isEmpty()) {
                throw new EmptyResultException("Department list is empty");
            }
            log.info("Found {} departments", departments.size());
            return departments;
        } catch (RuntimeException e) {
            throw new RuntimeException("Unexpected Error in findAll in DepartmentServiceImpl", e);
        }
    }

    @Override
    public Department addDepartment(Department department) {
        try {
            log.info("Adding department {}", department);
            return departmentRepository.saveAndFlush(department);
        }  catch (DataAccessException e) {
            log.warn("Warn in adding department {}", department, e);
            throw new RuntimeException("ERROR in adding department " + department, e);
        } catch (RuntimeException e) {
            log.warn("Unexpected exception while adding department {}", department, e);
            throw new RuntimeException("Unexpected Error adding department", e);
        }
    }
}
