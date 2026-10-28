package ru.dmc3105.webapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import ru.dmc3105.webapp.model.Task;
import ru.dmc3105.webapp.model.TaskPriority;
import ru.dmc3105.webapp.model.TaskStatus;
import ru.dmc3105.webapp.request.TaskFilter;
import ru.dmc3105.webapp.request.TaskQuery;
import ru.dmc3105.webapp.request.TaskRegisterRequest;
import ru.dmc3105.webapp.request.TaskUpdateRequest;
import ru.dmc3105.webapp.response.TaskPageResponse;
import ru.dmc3105.webapp.service.TaskService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskRestController {
    private final TaskService service;

    @GetMapping("/search")
    public TaskPageResponse search(
            @RequestParam(required = false) String query,
            @RequestParam(required = false) TaskStatus status,
            @RequestParam(required = false) TaskPriority priority,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDate dueBefore,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "desc") String direciton
            )
    {
        TaskFilter taskFilter = new TaskFilter(query, status, priority, dueBefore);
        TaskQuery taskQuery = TaskQuery.of(page, size, sortBy, direciton);
        var taskPage = service.search(taskFilter, taskQuery);
        return TaskPageResponse.of(taskPage);
    }

    @GetMapping
    public List<Task> getAllTasks()
    {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id)
    {
        return service.findById(id);
    }

    @PostMapping
    public Task store(@RequestBody TaskRegisterRequest request)
    {
        return service.create(request.toEntity());
    }

    @PutMapping("/{id}")
    public Task update(@RequestBody TaskUpdateRequest request, @PathVariable Long id)
    {
        return service.update(request.toEntity(id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id)
    {
        service.delete(id);
    }
}
