package com.fapah.crud.controller;

import com.fapah.crud.exception.NullParameterException;
import com.fapah.crud.entity.Task;
import com.fapah.crud.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/task")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping("/")
    public ResponseEntity<List<Task>> getAllTasks() {
        return ResponseEntity.ok(taskService.findAll());
    }

    @PostMapping("/add")
    public ResponseEntity<Task> addTask(@RequestBody(required = true) Task task) {
        try {
            return ResponseEntity.ok(taskService.save(task));
        } catch (Exception e) {
            throw new NullParameterException("One of the parameters is null.");
        }
    }

    @PostMapping("/addEmployeeToTask")
    public ResponseEntity<Task> addEmployeeToTask(@RequestParam(required = true)long employeeId,
                                                  @RequestParam(required = true) long taskId) {
        try {
            return ResponseEntity.ok(taskService.addEmployeeToTask(employeeId, taskId));
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}
