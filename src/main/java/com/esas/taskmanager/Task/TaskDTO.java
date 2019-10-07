package com.esas.taskmanager.Task;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class TaskDTO {

    @NotBlank
    private String title;
    private String description;
    @Enum(enumClass = Priority.class, message = "allowed values: {LOW, MEDIUM, HIGH}")
    private String priority;
    @Enum(enumClass = Status.class, message = "allowed values: {TODO, INPROGRESS, DONE}")
    private String status;
    @Basic
    private LocalDateTime deadline;
    private Long assigneeID;

    @Basic
    private LocalDateTime creationDate;

}
