package com.fapah.crud.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long employeeId;

    @Column(name = "employee_firstname", nullable = false)
    private String firstName;

    @Column(name = "employee_lastname", nullable = false)
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "department_id", referencedColumnName = "departmentId")
    private Department department;

    @ManyToOne
    @JoinColumn(name = "task_id", referencedColumnName = "taskId")
    private Task task;
}
