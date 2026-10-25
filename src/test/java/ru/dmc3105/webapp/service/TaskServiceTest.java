package ru.dmc3105.webapp.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.dmc3105.webapp.model.Task;
import ru.dmc3105.webapp.model.TaskPriority;
import ru.dmc3105.webapp.model.TaskStatus;
import ru.dmc3105.webapp.repository.TaskRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TaskServiceTest {
    private TaskRepository repository;
    private TaskService service;

    @BeforeEach
    void setUp() {
        repository = mock(TaskRepository.class);
        service = new TaskService(repository);
    }

    @Test
    void findAllReturnsTasksFromRepository() {
        //arange
        Task first = new Task(
                null,
                "title1",
                "description1",
                TaskStatus.NEW,
                TaskPriority.LOW,
                null,
                null
        );

        Task second = new Task(
                null,
                "title2",
                "description2",
                TaskStatus.NEW,
                TaskPriority.LOW,
                null,
                null
        );
        when(repository.findAllByOrderByCreatedAtDesc()).thenReturn((List.of(first,second)));

        //act
        List<Task> result = service.findAll();

        //assert
        assertEquals(2, result.size());
        assertSame(first, result.get(0));
        assertSame(second, result.get(1));

        verify(repository).findAllByOrderByCreatedAtDesc();
    }
}