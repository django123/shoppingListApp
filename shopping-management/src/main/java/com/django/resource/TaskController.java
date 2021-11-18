package com.django.resource;

import com.django.domain.Task;
import com.django.service.impl.TaskServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    private final TaskServiceImpl taskService;

    public TaskController(TaskServiceImpl taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<Task>>getAllTask(){
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Task>getOneTask(String id){
        return ResponseEntity.ok(taskService.getOneTask(id));
    }

    @PostMapping("/save")
    public ResponseEntity<Task> createTask(Task task){
        return ResponseEntity.ok(taskService.createTask(task));
    }

    @PutMapping("/update")
    public ResponseEntity<Task> updateTask(Task task){
        return ResponseEntity.ok(taskService.updateTask(task));
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable("id") String id){
       return ResponseEntity.ok(taskService.deleteTask(id));
    }

    @GetMapping("/activate/{id}")
    public ResponseEntity<Object> activateTask(@PathVariable("id") String id){
        return ResponseEntity.ok(taskService.activeTask(id));
    }
}
