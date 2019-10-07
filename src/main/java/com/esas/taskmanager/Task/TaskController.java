package com.esas.taskmanager.Task;

import com.esas.taskmanager.User.UserNotFoundException;
import com.esas.taskmanager.User.UserService;
import com.esas.taskmanager.util.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/task")
@CrossOrigin(origins = "http://localhost:4200")
public class TaskController {

    private TaskService taskService;
    private UserService userService;

    @Autowired
    public TaskController(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }

    @GetMapping("/created")
    public List<Task> getByCreator(Principal principal) throws NoTaskCreatedByUserException {
        return taskService.findByCreator(principal.getName());
    }

    @GetMapping("/assigned")
    public List<Task> getByAssigneeID(Principal principal) throws Exception {
        return taskService.findByAssigneeID(userService.findUserId(principal.getName()));
    }

    @GetMapping("/userTasks")
    public List<Task> getAllByCreatedAndAssigneeId(Principal principal) throws Exception {
        List<Task> tasks = new ArrayList<>();
        tasks.addAll(taskService.findByCreator(principal.getName()));
        tasks.addAll(taskService.findByAssigneeID(userService.findUserId(principal.getName())));
        return tasks;
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Task> getAll() {
        return taskService.findAll();
    }

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.OK)
    public void postNew(@RequestBody TaskDTO taskDTO){

    }

    @ResponseBody
    @ExceptionHandler(NoTaskAssignedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ErrorResponse noTaskAssignedHandler(NoTaskAssignedException ex){
        return new ErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ErrorResponse userNotFoundExceptionHandler(UserNotFoundException ex){
        return new ErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @ExceptionHandler(NoTaskCreatedByUserException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ErrorResponse noTaskCreatedExceptionHandler(NoTaskCreatedByUserException ex){
        return new ErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
