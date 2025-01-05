package com.fapah.crud.service;

import com.fapah.crud.exception.EmptyResultException;
import com.fapah.crud.entity.Employee;
import com.fapah.crud.entity.Task;
import com.fapah.crud.exception.NoSuchDataException;
import com.fapah.crud.repository.EmployeeRepository;
import com.fapah.crud.repository.TaskRepository;
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
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    private final EmployeeRepository employeeRepository;


    @Override
    public List<Task> findAll() {
        try {
            log.info("Find all task in TaskServiceImpl");
            List<Task> tasks = taskRepository.findAll();
            if (tasks.isEmpty()) {
                log.debug("Throw EmptyResultException in findAll in TaskServiceImpl");
                throw new EmptyResultException("Task list is empty");
            }
            log.info("Found {} tasks", tasks.size());
            return tasks;
        } catch (RuntimeException e) {
            log.debug("Error in findAll in TaskServiceImpl", e);
            throw new RuntimeException("Error in findAll in TaskServiceImpl", e);
        }
    }

    @Override
    public Task save(Task task) {
        try {
            log.info("Save task {} in TaskServiceImpl", task);
            return taskRepository.save(task);
        } catch (RuntimeException e) {
            log.debug("Error while saving task {}", task, e);
            throw new RuntimeException("Error in save in TaskServiceImpl", e);
        }
    }

    @Transactional
    @Override
    public Task addEmployeeToTask(long employeeId, long taskId) {
        try {
            Optional<Employee> employee = employeeRepository.findById(employeeId);
            Optional<Task> task = taskRepository.findById(taskId);
            task.get().getEmployees().add(employee.get());
            employee.get().setTask(task.get());
            taskRepository.save(task.get());
            log.info("Employee {} added to task {}", employeeId, taskId);
            return task.get();
        } catch (NoSuchElementException e) {
            log.debug("No such employee with id {} or task with id {}", employeeId, taskId);
            throw new NoSuchDataException("employee or task not found");
        } catch (RuntimeException e) {
            log.debug("Unexpected error in addEmployeeToTask in TaskServiceImpl", e);
            throw new RuntimeException("Unexpected Error adding employee to task", e);
        }
    }
}
