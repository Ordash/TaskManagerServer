package com.esas.taskmanager.Task;

import com.esas.taskmanager.Task.TaskRepository;
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

    public List<Task> findUserTasks(Long id) throws UserNotFound {
        return taskRepository.findByUser_Id(id).orElseThrow(() -> new UserNotFound("User cannot be found " + id));
    }
}
