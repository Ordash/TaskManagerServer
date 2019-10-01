package com.esas.taskmanager.Comment;

import com.esas.taskmanager.Task.Task;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    private Task taskComment;

    @Basic
    private LocalDateTime creationDate;
}
