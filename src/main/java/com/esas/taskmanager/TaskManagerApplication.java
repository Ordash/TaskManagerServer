package com.esas.taskmanager;

import com.esas.taskmanager.Task.Priority;
import com.esas.taskmanager.Task.Status;
import com.esas.taskmanager.Task.Task;
import com.esas.taskmanager.Task.TaskRepository;
import com.esas.taskmanager.User.User;
import com.esas.taskmanager.User.UserRepository;
import com.esas.taskmanager.User.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;

@SpringBootApplication
public class TaskManagerApplication implements CommandLineRunner {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	private UserService userService;
	private TaskRepository taskRepository;

	@Autowired
	public TaskManagerApplication(UserService userService, TaskRepository taskRepository) {
		this.userService = userService;
		this.taskRepository = taskRepository;
	}
	public static void main(String[] args) {
		SpringApplication.run(TaskManagerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
        userService.save(new User("user1", "pw1"));
        userService.save(new User("user2", "pw2"));
        userService.save(new User("user3", "pw3"));

        taskRepository.save(new Task(userService.findById(1L), "Task1", "Do task1",
				Priority.HIGH, Status.TODO, LocalDateTime.now().plusHours(6L),
				2L));
		taskRepository.save(new Task(userService.findById(2L), "Task2", "Do task2",
				Priority.HIGH, Status.TODO, LocalDateTime.now().plusHours(4L),
				3L));
		taskRepository.save(new Task(userService.findById(1L), "Task3", "Do task3",
				Priority.HIGH, Status.TODO, LocalDateTime.now().plusHours(2L),
				3L));
		taskRepository.save(new Task(userService.findById(1L), "Task4", "Do task4",
				Priority.HIGH, Status.TODO, LocalDateTime.now().plusHours(2L),
				3L));
		taskRepository.save(new Task(userService.findById(3L), "Task5", "Do task5",
				Priority.HIGH, Status.TODO, LocalDateTime.now().plusHours(2L),
				1L));
		taskRepository.save(new Task(userService.findById(2L), "Task6", "Do task6",
				Priority.HIGH, Status.TODO, LocalDateTime.now().plusHours(2L),
				1L));

	}
}
