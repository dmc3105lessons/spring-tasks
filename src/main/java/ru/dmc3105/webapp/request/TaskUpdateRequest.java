package ru.dmc3105.webapp.request;

import ru.dmc3105.webapp.model.Task;
import ru.dmc3105.webapp.model.TaskPriority;
import ru.dmc3105.webapp.model.TaskStatus;

import java.time.LocalDate;

public record TaskUpdateRequest(
        String title,
        String description,
        TaskStatus status,
        TaskPriority priority,
        LocalDate dueDate
) {
    public Task toEntity(Long id) {
        return new Task(id,
                title,
                description,
                status,
                priority,
                dueDate,
                null);
    }
}
