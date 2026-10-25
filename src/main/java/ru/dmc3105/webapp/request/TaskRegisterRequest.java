package ru.dmc3105.webapp.request;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import ru.dmc3105.webapp.model.Task;
import ru.dmc3105.webapp.model.TaskPriority;
import ru.dmc3105.webapp.model.TaskStatus;

import java.time.LocalDate;

public record TaskRegisterRequest(
        String title,
        String description,
        TaskStatus status,
        TaskPriority priority,
        LocalDate dueDate
) {
    public Task toEntity()
    {
        return new Task(null,
                title,
                description,
                status,
                priority,
                dueDate,
                null);
    }
}
