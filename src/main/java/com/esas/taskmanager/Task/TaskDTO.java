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
    private Priority priority;
    @NotBlank
    private Status status;
    @Basic
    private LocalDateTime deadline;
    private Long assigneeID;

    @Basic
    private LocalDateTime creationDate;

}
