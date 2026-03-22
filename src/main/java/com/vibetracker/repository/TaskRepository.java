package com.vibetracker.repository;

import com.vibetracker.model.Task;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class TaskRepository {

    private final Map<String, Task> tasks = new ConcurrentHashMap<>();

    public List<Task> findAll(Boolean completed) {
        return tasks.values().stream()
                .filter(t -> completed == null || t.isCompleted() == completed)
                .sorted(Comparator.comparing(Task::getCreatedAt).reversed())
                .toList();
    }

    public Optional<Task> findById(String id) {
        return Optional.ofNullable(tasks.get(id));
    }

    public Task save(Task task) {
        tasks.put(task.getId(), task);
        return task;
    }

    public boolean deleteById(String id) {
        return tasks.remove(id) != null;
    }
}
