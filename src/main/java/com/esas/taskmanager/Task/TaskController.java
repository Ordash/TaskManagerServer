package com.esas.taskmanager.Task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
@CrossOrigin(origins = "http://localhost:4200")
public class TaskController {

    private TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Task> getUserTasks(@PathVariable Long id) throws UserNotFound {
        return taskService.findUserTasks(id);
    }
}
