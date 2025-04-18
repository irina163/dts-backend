package uk.gov.hmcts.reform.dev.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import uk.gov.hmcts.reform.dev.models.Task;
import uk.gov.hmcts.reform.dev.repository.TaskRepository;

import java.util.List;
import java.util.Optional;
import java.util.Objects;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public void createTask(Task newTask) {
        taskRepository.save(newTask);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Optional<Task> getTask(Long id) {
        return taskRepository.findById(id);
    }

    public Task updateTask(Task newTask, Long id) {
        return taskRepository.findById(id)
        .map(oldTask -> {
            oldTask.setTitle(Objects.requireNonNullElse(newTask.getTitle(), oldTask.getTitle()));
            oldTask.setDescription(Objects.requireNonNullElse(newTask.getDescription(), oldTask.getDescription()));
            oldTask.setStatus(Objects.requireNonNullElse(newTask.getStatus(), oldTask.getStatus()));
            oldTask.setDueDate(Objects.requireNonNullElse(newTask.getDueDate(), oldTask.getDueDate()));
            Task updatedTask = taskRepository.save(oldTask);
            return updatedTask;
        })
        .orElseGet(() -> {
            Task createTask = taskRepository.save(newTask);
            return createTask;
        });
    }

    public void deleteById(Long id) {
        taskRepository.deleteById(id);
    }



}