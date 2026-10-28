package ru.dmc3105.webapp.repository;

import org.springframework.data.jpa.domain.Specification;
import ru.dmc3105.webapp.model.Task;
import ru.dmc3105.webapp.model.TaskPriority;
import ru.dmc3105.webapp.model.TaskStatus;
import ru.dmc3105.webapp.request.TaskFilter;

import java.time.LocalDate;

public final class TaskSpecifications {
    private TaskSpecifications() {

    }

    public static Specification<Task> titleContains(String title) {
        var pattern = "%" + title.toLowerCase() + "%";
        return ((root, query, cb) ->  cb.like(cb.lower(root.get("title")), pattern));
    }

    public static Specification<Task> hasStatus(TaskStatus status) {
        return (root, query, cb) -> cb.equal(root.get("status"), status);

    }

    public static Specification<Task> hasPriority(TaskPriority priority) {
        return (root, query, cb) -> cb.equal(root.get("priority"), priority);
    }

    public static Specification<Task> dueBefore(LocalDate date) {
        return (root, query, cb) -> cb.lessThanOrEqualTo(root.get("due"), date);
    }

    public static Specification<Task> matching(TaskFilter filter) {
        Specification<Task> spec = Specification.unrestricted();
        if (filter.query() != null) {
            spec = spec.and(TaskSpecifications.titleContains(filter.query()));
        }
        if (filter.status() != null) {
            spec = spec.and(TaskSpecifications.hasStatus(filter.status()));
        }
        if (filter.priority() != null) {
            spec = spec.and(TaskSpecifications.hasPriority(filter.priority()));
        }
        if (filter.dueBefore() != null) {
            spec = spec.and(TaskSpecifications.dueBefore(filter.dueBefore()));
        }

        return spec;
    }
}
