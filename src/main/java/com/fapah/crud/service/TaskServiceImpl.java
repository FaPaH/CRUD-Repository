package com.fapah.crud.service;

import com.fapah.crud.exception.EmptyResultException;
import com.fapah.crud.entity.Employee;
import com.fapah.crud.entity.Task;
import com.fapah.crud.repository.EmployeeRepository;
import com.fapah.crud.repository.TaskRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    private final EmployeeRepository employeeRepository;


    @Override
    public List<Task> findAll() {
        List<Task> tasks = taskRepository.findAll();
        if (tasks.isEmpty()) {
            throw new EmptyResultException("Department list is empty");
        }
        log.info("Found {} tasks", tasks.size());
        return tasks;
    }

    @Override
    public Task save(Task task) {
        return taskRepository.save(task);
    }

    @Transactional
    @Override
    public Task addEmployeeToTask(long employeeId, long taskId) {
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        Optional<Task> task = taskRepository.findById(taskId);
        if (employee.isPresent() && task.isPresent()) {
            task.get().getEmployees().add(employee.get());
            employee.get().setTask(task.get());
            taskRepository.save(task.get());
        }
        log.info("Employee {} added to task {}", employeeId, taskId);
        return task.get();
    }
}
