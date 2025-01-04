package com.fapah.crud.service;

import com.fapah.crud.entity.Department;

import java.util.List;

public interface DepartmentService {

    public List<Department> findAll();

    public Department addDepartment(Department department);
}
