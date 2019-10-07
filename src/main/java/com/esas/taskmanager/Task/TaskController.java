package com.esas.taskmanager.Task;

import com.esas.taskmanager.util.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/task")
@CrossOrigin(origins = "http://localhost:4200")
public class TaskController {

    private TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/userRelated")
    public List<Task> getAllByCreatorAndAssignee(Principal principal) {
        return taskService.findByCreatorAndAssignee(principal.getName());
    }

    @GetMapping("/created")
    public List<Task> getByCreator(Principal principal) {
        return taskService.findByCreator(principal.getName());
    }

    @GetMapping("/assigned")
    public List<Task> getByAssignee(Principal principal) {
        return taskService.findByAssignee(principal.getName());
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Task> getAll() {
        return taskService.findAll();
    }

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.OK)
    public Task postNew(@RequestBody @Valid TaskDTO taskDTO, Principal principal) {
        return taskService.save(taskDTO, principal.getName());
    }

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ErrorResponse invalidFieldHandler(MethodArgumentNotValidException ex) {
        String fieldErrors = ex.getBindingResult().getFieldErrors().stream()
                .map(f -> f.getField() + " " + f.getDefaultMessage())
                .collect(Collectors.joining(", "));
        return new ErrorResponse(fieldErrors, HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ErrorResponse userNotFoundExceptionHandler(UsernameNotFoundException ex){
        return new ErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
