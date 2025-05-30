package com.david.agendadortarefas.controller;

import com.david.agendadortarefas.business.TaskService;
import com.david.agendadortarefas.business.dto.TaskDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/task")
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskDTO> createTask(
            @RequestBody TaskDTO taskDTO,
            @RequestHeader("Authorization") String token
    ) {
        return ResponseEntity.ok(taskService.createTask(taskDTO, token));
    }
}
