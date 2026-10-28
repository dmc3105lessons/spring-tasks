INSERT INTO tasks (title, description, status, priority, due_date, created_at)
VALUES
    ('Настроить CI/CD',
     'Настроить пайплайн сборки и деплоя в GitLab CI для основного сервиса.',
     'NEW', 'HIGH', date('now', '+3 day'), CURRENT_TIMESTAMP),

    ('Написать документацию API',
     'Описать все эндпоинты контроллера задач в OpenAPI/Swagger.',
     'IN_PROGRESS', 'MEDIUM', date('now', '+7 day'), CURRENT_TIMESTAMP),

    ('Исправить баг с датой создания',
     'При обновлении задачи поле createdAt перезаписывается. Проверить @PrePersist.',
     'COMPLETED', 'HIGH', date('now', '-1 day'), CURRENT_TIMESTAMP),

    ('Добавить пагинацию',
     'Реализовать постраничный вывод списка задач через Pageable.',
     'NEW', 'LOW', NULL, CURRENT_TIMESTAMP),

    ('Ревью пул-реквеста #42',
     'Проверить изменения в слое репозиториев и написать комментарии.',
     'IN_PROGRESS', 'HIGH', date('now', '+1 day'), CURRENT_TIMESTAMP);