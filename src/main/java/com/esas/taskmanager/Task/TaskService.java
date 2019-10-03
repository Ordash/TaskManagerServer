package com.esas.taskmanager.Task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> findAll(){
        return taskRepository.findAll();
    }

    public List<Task> findByCreator(String username) throws NoTaskCreatedByUserException {
        return taskRepository.findByCreator_Username(username)
                .orElseThrow(() -> new NoTaskCreatedByUserException("No task created by the user " + username));
    }

    public List<Task> findByAssigneeID(Long id) throws NoTaskAssignedException {
        return taskRepository.findByAssigneeID(id).orElseThrow(() -> new NoTaskAssignedException("No task assigned for assigneeID " + id));
    }
}
