package com.david.agendadortarefas.business.mapper;

import com.david.agendadortarefas.business.dto.TaskDTO;
import com.david.agendadortarefas.infrastructure.entity.Task;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskConverter {

    Task toTask(TaskDTO taskDTO);

    TaskDTO toTaskDTO(Task task);

    List<TaskDTO> toTaskDTOList(List<Task> taskList);
}
