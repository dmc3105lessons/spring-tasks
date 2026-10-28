package ru.dmc3105.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.dmc3105.webapp.model.Task;
import ru.dmc3105.webapp.model.TaskStatus;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>, JpaSpecificationExecutor<Task> {
    List<Task> findAllByOrderByCreatedAtDesc();
    List<Task> findByStatusOrderByCreatedAtDesc(TaskStatus status);
}
