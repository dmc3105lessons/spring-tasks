package ru.dmc3105.webapp.request;

import ru.dmc3105.webapp.model.TaskPriority;
import ru.dmc3105.webapp.model.TaskStatus;

import java.time.LocalDate;

public record TaskFilter(
        String query,
        TaskStatus status,
        TaskPriority priority,
        LocalDate dueBefore
) {
    public TaskFilter {
        query = (query == null || query.isBlank()) ? null : query.trim();
    }

    public static TaskFilter empty() {
        return new TaskFilter(null, null, null, null);
    }
}
