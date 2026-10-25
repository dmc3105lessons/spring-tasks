package ru.dmc3105.webapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.dmc3105.webapp.model.Task;
import ru.dmc3105.webapp.request.TaskRegisterRequest;
import ru.dmc3105.webapp.request.TaskUpdateRequest;
import ru.dmc3105.webapp.service.TaskService;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskRestController {
    private final TaskService service;

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
