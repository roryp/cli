package com.vibetracker.controller;

import com.vibetracker.model.Task;
import com.vibetracker.repository.TaskRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskRepository repository;

    public TaskController(TaskRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Task> getAllTasks(@RequestParam(required = false) Boolean completed) {
        return repository.findAll(completed);
    }

    @GetMapping("/{id}")
    public Task getTask(@PathVariable String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found"));
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Map<String, String> body) {
        String title = body.get("title");
        if (title == null || title.trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Title is required and must be a non-empty string");
        }

        String description = body.get("description");
        Task task = new Task(title.trim(), description != null ? description.trim() : "");
        repository.save(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(task);
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable String id, @RequestBody Map<String, Object> body) {
        Task task = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found"));

        if (body.containsKey("title")) {
            String title = (String) body.get("title");
            if (title == null || title.trim().isEmpty()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Title must be a non-empty string");
            }
            task.setTitle(title.trim());
        }
        if (body.containsKey("description")) {
            task.setDescription((String) body.get("description"));
        }
        if (body.containsKey("completed")) {
            task.setCompleted((Boolean) body.get("completed"));
        }

        task.setUpdatedAt(Instant.now());
        return repository.save(task);
    }

    @PatchMapping("/{id}/toggle")
    public Task toggleTask(@PathVariable String id) {
        Task task = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found"));

        task.setCompleted(!task.isCompleted());
        task.setUpdatedAt(Instant.now());
        return repository.save(task);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable String id) {
        if (!repository.deleteById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found");
        }
    }
}
