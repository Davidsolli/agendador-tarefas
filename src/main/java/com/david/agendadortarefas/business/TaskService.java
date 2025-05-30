package com.david.agendadortarefas.business;

import com.david.agendadortarefas.business.dto.TaskDTO;
import com.david.agendadortarefas.business.mapper.TaskConverter;
import com.david.agendadortarefas.infrastructure.entity.Task;
import com.david.agendadortarefas.infrastructure.enums.TaskStatusEnum;
import com.david.agendadortarefas.infrastructure.repository.TaskRepository;
import com.david.agendadortarefas.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskConverter taskConverter;
    private final JwtUtil jwtUtil;

    public TaskDTO createTask(TaskDTO taskDTO, String token) {
        taskDTO.setCreateAt(LocalDateTime.now());
        taskDTO.setTaskStatusEnum(TaskStatusEnum.PENDENT);

        String email = jwtUtil.extractUsername(token.substring(7));
        taskDTO.setUserEmail(email);

        Task task = taskRepository.save(taskConverter.toTask(taskDTO));
        return taskConverter.toTaskDTO(task);
    }
}
