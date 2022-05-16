package com.farsousa.taskapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.farsousa.taskapi.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long>{
	
	public List<Task> findByStatus(String status);

}
