package ru.dmc3105.webapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dmc3105.webapp.exception.TaskNotFoundException;
import ru.dmc3105.webapp.model.Task;
import ru.dmc3105.webapp.model.TaskStatus;
import ru.dmc3105.webapp.repository.TaskRepository;
import ru.dmc3105.webapp.repository.TaskSpecifications;
import ru.dmc3105.webapp.request.TaskFilter;
import ru.dmc3105.webapp.request.TaskQuery;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TaskService {
    private final TaskRepository taskRepository;

    public Page<Task> search(TaskFilter taskFilter, TaskQuery taskQuery) {
        var specification = TaskSpecifications.matching(taskFilter);
        var pageable = taskQuery.toPageable();
        return taskRepository.findAll(specification, pageable);
    }

    public List<Task> findAll() {
        return taskRepository.findAllByOrderByCreatedAtDesc();
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

    @Transactional
    public Task update(Task entity) {
        return taskRepository.save(entity);
    }
}
