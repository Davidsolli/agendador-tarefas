package com.david.agendadortarefas.infrastructure.repository;

import com.david.agendadortarefas.infrastructure.entity.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TaskRepository extends MongoRepository<Task, String> {

    List<Task> findByEventDateBetween(LocalDateTime initialDate, LocalDateTime finalDate);

    List<Task> findByUserEmail(String userEmail);
}
