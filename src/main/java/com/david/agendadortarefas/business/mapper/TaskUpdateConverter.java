package com.david.agendadortarefas.business.mapper;

import com.david.agendadortarefas.business.dto.TaskDTO;
import com.david.agendadortarefas.infrastructure.entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TaskUpdateConverter {

    void taskUpdate(TaskDTO taskDTO, @MappingTarget Task task);
}
