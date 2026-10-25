package ru.dmc3105.webapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dmc3105.webapp.exception.TaskNotFoundException;
import ru.dmc3105.webapp.model.Task;
import ru.dmc3105.webapp.model.TaskStatus;
import ru.dmc3105.webapp.repository.TaskRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TaskService {
    private final TaskRepository taskRepository;

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public List<Task> findByStatus(TaskStatus status)
    {
        return taskRepository.findByStatusOrderByCreatedAtDesc(status);
    }

    public Task findById(Long id) {
        return taskRepository.findById(id).orElseThrow(TaskNotFoundException::new);
    }

    @Transactional
    public Task create(Task task) {
        return taskRepository.save(task);
    }

    @Transactional
    public void delete(Long id) {
        Task task = findById(id);
        taskRepository.delete(task);
    }
}
