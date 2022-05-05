package com.farsousa.taskapi.dto.response;

import java.util.List;
import java.util.stream.Collectors;

import com.farsousa.taskapi.model.Task;

public class TaskResponse {
	
	private Long id;
	private String title;
	private String description;
	private String status;
	private String criationDate;
	private String conclusionDate;
	
	public TaskResponse(Task task) {
		this.id = task.getId();
		this.title = task.getTitle();
		this.description = task.getDescription();
		this.status = task.getStatus() + "";
		this.criationDate = task.getCriationDate() + "";
		this.conclusionDate = task.getConclusionDate() + "";
	}
	
	public static List<TaskResponse> convertTaskListToTaskResponseList(List<Task> tasks) {
		return tasks.stream().map(TaskResponse::new).collect(Collectors.toList());
	}

	public String getStatus() {
		return status;
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public String getCriationDate() {
		return criationDate;
	}

	public String getConclusionDate() {
		return conclusionDate;
	}
	
	

}
