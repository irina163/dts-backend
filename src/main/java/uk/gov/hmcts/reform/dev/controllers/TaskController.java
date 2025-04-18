package uk.gov.hmcts.reform.dev.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import static org.springframework.http.ResponseEntity.ok;

import uk.gov.hmcts.reform.dev.models.Task;
import uk.gov.hmcts.reform.dev.service.TaskService;

//import java.time.LocalDateTime;
import java.util.List;

@RestController
public class TaskController {

    private final TaskService taskService;

    TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/create")
    public void createTask(@RequestBody Task newTask) {
        taskService.createTask(newTask);
    }

    @GetMapping(value = "/tasks", produces = "application/json")
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }

    @GetMapping(value = "/tasks/{id}", produces = "application/json")
    public ResponseEntity<Task> getTask(@PathVariable Long id) {
        return taskService.getTask(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping(value = "/tasks/{id}", produces = "application/json")
    public ResponseEntity<Task> updateTask(@RequestBody Task newTask, @PathVariable Long id) {
        Task updatedTask = taskService.updateTask(newTask, id);
        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping("/tasks/{id}")
    void deleteTask(@PathVariable Long id) {
        taskService.deleteById(id);
    }

}
