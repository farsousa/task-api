package com.farsousa.taskapi.dto.request.update;

import com.farsousa.taskapi.enumeration.StatusTask;
import com.farsousa.taskapi.model.Task;

public class TaskRequetUpdate {
	
	private String title;
	private String description;
	private String status;
	
	public TaskRequetUpdate() {
		
	}
	
	public Task convertTaskRequestSaveToTask() {
		StatusTask statusTask = StatusTask.Aberta;
		if (status.equals("Fazendo")) statusTask = StatusTask.Fazendo;
		else if (statusTask.equals("Concluída")) statusTask = StatusTask.Concluída;
		return new Task(this.title, this.description, statusTask);
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
