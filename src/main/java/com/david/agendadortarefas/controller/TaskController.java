package com.david.agendadortarefas.controller;

import com.david.agendadortarefas.business.TaskService;
import com.david.agendadortarefas.business.dto.TaskDTO;
import com.david.agendadortarefas.infrastructure.enums.TaskStatusEnum;
import com.david.agendadortarefas.infrastructure.exceprions.ResourceNotFoundException;
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
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime initialDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime finalDate
    ) {
        return ResponseEntity.ok(taskService.findTasksByPeriod(initialDate, finalDate));
    }

    @GetMapping
    public ResponseEntity<List<TaskDTO>> findTasksByUserEmail(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(taskService.findTasksByUserEmail(token));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteTaskById(@RequestParam("id") String id) {
        try {
            taskService.deleteTaskById(id);
            return ResponseEntity.ok().build();
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("id não encontrado! " + e.getCause());
        }
    }

    @PatchMapping
    public ResponseEntity<TaskDTO> changeStatus(
            @RequestParam("status")TaskStatusEnum status,
            @RequestParam("id") String id
    ) {
        return ResponseEntity.ok(taskService.changeStatus(status, id));
    }

    @PutMapping
    public ResponseEntity<TaskDTO> updateTasks(@RequestBody TaskDTO taskDTO, @RequestParam("id") String id) {
        return ResponseEntity.ok(taskService.updateTasks(taskDTO, id));
    }
}
