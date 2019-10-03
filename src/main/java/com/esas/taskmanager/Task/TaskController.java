package com.esas.taskmanager.Task;

import com.esas.taskmanager.User.UserNotFound;
import com.esas.taskmanager.User.UserService;
import com.esas.taskmanager.util.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/task")
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

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Task> getAll(){
        return taskService.findAll();
    }

    @ResponseBody
    @ExceptionHandler(NoTaskAssignedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ErrorResponse noTaskAssignedHandler(NoTaskAssignedException ex){
        return new ErrorResponse(ex.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(UserNotFound.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ErrorResponse userNotFoundExceptionHandler(UserNotFound ex){
        return new ErrorResponse(ex.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(NoTaskCreatedByUserException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ErrorResponse noTaskCreatedExceptionHandler(NoTaskCreatedByUserException ex){
        return new ErrorResponse(ex.getMessage());
    }
}
