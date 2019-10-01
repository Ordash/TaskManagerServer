package com.esas.taskmanager.Task;

import com.esas.taskmanager.User.User;
import com.esas.taskmanager.Comment.Comment;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    private User creator;

    @OneToMany(mappedBy = "taskComment", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comment> commentsList;

    @NotBlank
    private String title;
    private String description;
    private Priority priority;
    private Status status;
    @Basic
    private LocalDateTime deadline;
    private Long assigneeID;

    @Basic
    private LocalDateTime creationDate;

    public Task(User creator, @NotBlank String title, String description, Priority priority, Status status, LocalDateTime deadline, Long assigneeID) {
        this.creator = creator;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.status = status;
        this.deadline = deadline;
        this.assigneeID = assigneeID;
        this.creationDate = LocalDateTime.now();
    }
}
