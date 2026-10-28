package ru.dmc3105.webapp.request;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Set;

public record TaskQuery(int page, int size, String sortBy, Sort.Direction direction) {
    private static final int DEFAULT_SIZE = 10;
    private static final int MAX_SIZE = 100;
    private static final Set<String> ALLOWED_SORT_FIELDS =  Set.of(
            "createdAt", "dueDate", "priority", "title", "status"
    );
    private static final String DEFAULT_SORT_FIELD = "createdAt";

    public TaskQuery {
        page = Math.max(0, page);
        size = size <= 0 ? DEFAULT_SIZE : Math.min(size, MAX_SIZE);
        sortBy = (sortBy != null && ALLOWED_SORT_FIELDS.contains(sortBy)) ? sortBy : DEFAULT_SORT_FIELD;
        direction = direction != null ? direction : Sort.Direction.ASC;
    }

    public static TaskQuery of(Integer page, Integer size, String sortBy, String direction) {
        Sort.Direction sortDirection;
        try {
            sortDirection = (direction == null || direction.isBlank())
                    ? Sort.Direction.DESC
                    : Sort.Direction.fromString(direction);
        } catch (IllegalArgumentException e) {
            sortDirection = Sort.Direction.DESC;
        }
        return new TaskQuery(page == null ? 0 : page, size == null ? DEFAULT_SIZE : size, sortBy, sortDirection);
    }

    public Pageable toPageable() {
        return PageRequest.of(page, size, Sort.by(direction, sortBy));
    }
}
