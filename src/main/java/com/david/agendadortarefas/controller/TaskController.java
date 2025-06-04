package com.david.agendadortarefas.controller;

import com.david.agendadortarefas.business.TaskService;
import com.david.agendadortarefas.business.dto.TaskDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

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

    @GetMapping("/events")
    public ResponseEntity<List<TaskDTO>> findTaskListByPeriod(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime initialDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime finalDate
    ) {
        return ResponseEntity.ok(taskService.findTasksByPeriod(initialDate, finalDate));
    }

    @GetMapping
    public ResponseEntity<List<TaskDTO>> findTasksByUserEmail(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(taskService.findTasksByUserEmail(token));
    }
}
