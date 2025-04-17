package uk.gov.hmcts.reform.dev.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.http.ResponseEntity.ok;

import uk.gov.hmcts.reform.dev.models.Task;
import uk.gov.hmcts.reform.dev.repository.TaskRepository;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class TaskController {

    @Autowired
    private final TaskRepository taskRepository;

    TaskController(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    @PostMapping("/create")
    public Task createTask(@RequestBody Task newTask) {
        return taskRepository.save(newTask);
    }

    @GetMapping(value = "/tasks", produces = "application/json")
    public ResponseEntity<List<Task>> getAllTasks(){
        List<Task> tasks = taskRepository.findAll();
        return ResponseEntity.ok(tasks);
    }

    @GetMapping(value = "/tasks/{id}", produces = "application/json")
    public ResponseEntity<Task> getTask(@PathVariable Long id) {
        return taskRepository.findById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping(value = "/tasks/{id}", produces = "application/json")
    public ResponseEntity<Task> upateTask(@RequestBody Task newTask, @PathVariable Long id){
        return taskRepository.findById(id)
        .map(oldTask -> {
            oldTask.setTitle(newTask.getTitle() != null ? newTask.getTitle() : oldTask.getTitle());
            oldTask.setDescription(newTask.getDescription() != null ? newTask.getDescription() : oldTask.getDescription());
            oldTask.setStatus(newTask.getStatus()!= null ? newTask.getStatus() : oldTask.getStatus());
            oldTask.setDueDate(newTask.getDueDate()!= null ? newTask.getDueDate() : oldTask.getDueDate());
            Task updatedTask = taskRepository.save(oldTask);
            return ResponseEntity.ok(updatedTask);
        })
        .orElseGet(() -> {
            Task createTask = taskRepository.save(newTask);
            return ResponseEntity.ok(createTask);
      });
    }

    @DeleteMapping("/tasks/{id}")
    void deleteTask(@PathVariable Long id) {
        taskRepository.deleteById(id);
    }

}
