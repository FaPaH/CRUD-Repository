package com.fapah.crud.repository;

import com.fapah.crud.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
