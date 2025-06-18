package com.david.agendadortarefas.infrastructure.repository;

import com.david.agendadortarefas.infrastructure.entity.Task;
import com.david.agendadortarefas.infrastructure.enums.TaskStatusEnum;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TaskRepository extends MongoRepository<Task, String> {

    List<Task> findByEventDateBetweenAndTaskStatusEnum(
            LocalDateTime initialDate,
            LocalDateTime finalDate,
            TaskStatusEnum status
    );

    List<Task> findByUserEmail(String userEmail);
}
