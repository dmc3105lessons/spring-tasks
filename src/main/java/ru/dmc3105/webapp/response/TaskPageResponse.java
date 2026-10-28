package ru.dmc3105.webapp.response;

import org.springframework.data.domain.Page;
import ru.dmc3105.webapp.model.Task;

import java.util.List;

public record TaskPageResponse(
        List<Task> items,
        int page,
        int size,
        long totalElements,
        int totalPages,
        boolean hasNext,
        boolean hasPrevious
) {
    public static TaskPageResponse of(Page<Task> page) {
        return new TaskPageResponse(
                page.getContent(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.hasNext(),
                page.hasPrevious()
        );
    }
}
