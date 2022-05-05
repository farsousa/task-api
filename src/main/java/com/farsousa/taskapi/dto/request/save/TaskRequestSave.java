package com.farsousa.taskapi.dto.request.save;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.farsousa.taskapi.enumeration.StatusTask;
import com.farsousa.taskapi.model.Task;

public class TaskRequestSave {
	
	@NotNull
	@NotBlank
	private String title;
	@NotNull
	@NotBlank
	private String description;
	
	public Task convertTaskRequestSaveToTask() {
		return new Task(this.title, this.description, StatusTask.Aberta);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
