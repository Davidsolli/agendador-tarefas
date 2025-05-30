package com.david.agendadortarefas.infrastructure.entity;

import com.david.agendadortarefas.infrastructure.enums.TaskStatusEnum;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document("task")
public class Task {

    @Id
    private String id;
    private String taskName;
    private String description;
    private String userEmail;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private LocalDateTime eventDate;
    private TaskStatusEnum taskStatusEnum;
}
