package com.esas.taskmanager.Task;

import com.esas.taskmanager.User.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {

    private TaskRepository taskRepository;
    private UserService userService;
    private ModelMapper modelMapper;

    @Autowired
    public TaskService(TaskRepository taskRepository, UserService userService, ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.taskRepository = taskRepository;
    }

    public List<Task> findAll(){
        return taskRepository.findAll();
    }

    public List<Task> findByCreator(String username) {
        return taskRepository.findByCreator_Username(username)
                .orElse(new ArrayList<>());
    }

    public List<Task> findByAssignee(String username) {
        return taskRepository.findByAssigneeID(userService.findUserId(username))
                .orElse(new ArrayList<>());
    }

    public List<Task> findByCreatorAndAssignee(String username) {
        List<Task> userRelatedTasks = new ArrayList<>();
        userRelatedTasks.addAll(findByCreator(username));
        userRelatedTasks.addAll(findByAssignee(username));
        return userRelatedTasks;
    }

    public Task save(TaskDTO taskDTO, String username) {
        Task task = new Task();
        modelMapper.map(taskDTO, task);
        System.out.println(task.getTitle() + "!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        task.setCreator(userService.findByUsername(username));
        return taskRepository.save(task);
    }
}
