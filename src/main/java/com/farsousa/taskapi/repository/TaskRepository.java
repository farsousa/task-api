package com.farsousa.taskapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.farsousa.taskapi.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long>{

}
