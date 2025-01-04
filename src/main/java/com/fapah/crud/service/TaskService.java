package com.fapah.crud.service;

import com.fapah.crud.entity.Employee;
import com.fapah.crud.entity.Task;

import java.util.List;

public interface TaskService {

    public List<Task> findAll();

    public Task save(Task task);

    public Task addEmployeeToTask(long employeeId, long taskId);
}
