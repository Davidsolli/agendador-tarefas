package com.david.agendadortarefas.business;

import com.david.agendadortarefas.business.dto.TaskDTO;
import com.david.agendadortarefas.business.mapper.TaskConverter;
import com.david.agendadortarefas.business.mapper.TaskUpdateConverter;
import com.david.agendadortarefas.infrastructure.entity.Task;
import com.david.agendadortarefas.infrastructure.enums.TaskStatusEnum;
import com.david.agendadortarefas.infrastructure.exceprions.ResourceNotFoundException;
import com.david.agendadortarefas.infrastructure.repository.TaskRepository;
import com.david.agendadortarefas.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskConverter taskConverter;
    private final JwtUtil jwtUtil;
    private final TaskUpdateConverter taskUpdateConverter;

    public TaskDTO createTask(TaskDTO taskDTO, String token) {
        taskDTO.setCreateAt(LocalDateTime.now());
        taskDTO.setTaskStatusEnum(TaskStatusEnum.PENDENT);

        String email = jwtUtil.extractUsername(token.substring(7));
        taskDTO.setUserEmail(email);

        Task task = taskRepository.save(taskConverter.toTask(taskDTO));
        return taskConverter.toTaskDTO(task);
    }

    public List<TaskDTO> findTasksByPeriod(LocalDateTime initialDate, LocalDateTime finalDate) {
        return taskConverter.toTaskDTOList(taskRepository.findByEventDateBetween(initialDate, finalDate));
    }

    public List<TaskDTO> findTasksByUserEmail(String token) {
        String email = jwtUtil.extractUsername(token.substring(7));
        return taskConverter.toTaskDTOList(taskRepository.findByUserEmail(email));
    }

    public void deleteTaskById(String id) {
        taskRepository.deleteById(id);
    }

    public TaskDTO changeStatus(TaskStatusEnum status, String id) {
        try {
            Task task = taskRepository
                    .findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada! " + id));
            task.setTaskStatusEnum(status);
            return taskConverter.toTaskDTO(taskRepository.save(task));
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Erro ao alterar status de tarefa " + e.getCause());
        }
    }

    public TaskDTO updateTasks(TaskDTO taskDTO, String id) {
        try {
            Task task = taskRepository
                    .findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada! " + id));
            taskUpdateConverter.taskUpdate(taskDTO, task);
            return taskConverter.toTaskDTO(taskRepository.save(task));
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Erro ao alterar tarefa! " + e.getCause());
        }
    }
}
