package com.esas.taskmanager;

import com.esas.taskmanager.Task.Priority;
import com.esas.taskmanager.Task.Status;
import com.esas.taskmanager.Task.Task;
import com.esas.taskmanager.Task.TaskRepository;
import com.esas.taskmanager.User.User;
import com.esas.taskmanager.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@SpringBootApplication
public class TaskManagerApplication implements CommandLineRunner {

	private UserRepository userRepository;
	private TaskRepository taskRepository;

	@Autowired
	public TaskManagerApplication(UserRepository userRepository, TaskRepository taskRepository) {
		this.userRepository = userRepository;
		this.taskRepository = taskRepository;
	}
	public static void main(String[] args) {
		SpringApplication.run(TaskManagerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
        userRepository.save(new User("user1", "pw1"));
        userRepository.save(new User("user2", "pw2"));
        userRepository.save(new User("user3", "pw3"));
        taskRepository.save(new Task(userRepository.getOne(1L), "Task1", "Do task1",
				Priority.HIGH, Status.TODO, LocalDateTime.now().plusHours(6L),
				2L));
		taskRepository.save(new Task(userRepository.getOne(2L), "Task2", "Do task2",
				Priority.HIGH, Status.TODO, LocalDateTime.now().plusHours(4L),
				3L));
		taskRepository.save(new Task(userRepository.getOne(1L), "Task1", "Do task3",
				Priority.HIGH, Status.TODO, LocalDateTime.now().plusHours(2L),
				3L));
	}
}
