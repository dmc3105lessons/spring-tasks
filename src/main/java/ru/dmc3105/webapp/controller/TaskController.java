package ru.dmc3105.webapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.dmc3105.webapp.model.Task;
import ru.dmc3105.webapp.request.TaskRegisterRequest;
import ru.dmc3105.webapp.service.TaskService;

@Controller
@RequiredArgsConstructor
public class TaskController {
    private final TaskService service;

    @GetMapping("/")
    public String index() {
        return "redirect:/tasks";
    }

    @GetMapping("/tasks")
    public String index(Model model) {
        model.addAttribute("tasks", service.findAll());
        return "tasks/list";
    }

    @GetMapping("/tasks/{id}")
    public String details(@PathVariable Long id, Model model)
    {
        model.addAttribute("task", service.findById(id));
        return "tasks/details";
    }
}
