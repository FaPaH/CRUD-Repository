package com.fapah.crud.controller;

import com.fapah.crud.exception.NullParameterException;
import com.fapah.crud.entity.Task;
import com.fapah.crud.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/task")
@RequiredArgsConstructor
@Slf4j
public class TaskController {

    private final TaskService taskService;

    @GetMapping("/")
    public ResponseEntity<List<Task>> getAllTasks() {
        log.info("Getting all tasks");
        return ResponseEntity.ok(taskService.findAll());
    }

    @PostMapping("/add")
    public ResponseEntity<Task> addTask(@RequestBody(required = true) Task task) {
        try {
            log.info("Adding task {} in addTask", task);
            return ResponseEntity.ok(taskService.save(task));
        } catch (DataAccessException e) {
            log.warn("Error while adding task {}", task, e);
            throw new NullParameterException("One of the parameters is null.");
        }
    }

    @PostMapping("/addEmployeeToTask")
    public ResponseEntity<Task> addEmployeeToTask(@RequestParam(required = true)long employeeId,
                                                  @RequestParam(required = true) long taskId) {
        try {
            log.info("Adding employee {} to task {}", employeeId, taskId);
            return ResponseEntity.ok(taskService.addEmployeeToTask(employeeId, taskId));
        } catch (DataAccessException e) {
            log.warn("Error while adding employee {} to task {}", employeeId, taskId, e);
            throw new NullParameterException("One of the parameters is null.");
        }
    }
}
