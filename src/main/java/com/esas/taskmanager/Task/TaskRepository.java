package com.esas.taskmanager.Task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    Optional<List<Task>> findByCreator_Username(String username);
    Optional<List<Task>> findByAssigneeID(Long id);
}
