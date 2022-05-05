package com.farsousa.taskapi.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.farsousa.taskapi.enumeration.StatusTask;

@Entity
public class Task {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique=true, nullable=false)
	private String title;
	@Column(unique=false, nullable=false)
	private String description;
	@Column(unique=false, nullable=false)
	@Enumerated(EnumType.STRING)
	private StatusTask status;
	@Column(unique=false, nullable=false)
	private LocalDate criationDate;
	@Column(unique=false, nullable=true)
	private LocalDate conclusionDate;
	
	public Task() {
		
	}
	
	public Task(String title, String description, StatusTask status) {
		this.title = title;
		this.description = description;
		this.status = status;
		this.criationDate = LocalDate.now();
		if (status == StatusTask.Concluída) 
			this.conclusionDate = LocalDate.now();
		else 
			this.conclusionDate = null;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public StatusTask getStatus() {
		return status;
	}

	public void setStatus(StatusTask status) {
		this.status = status;
	}

	public LocalDate getCriationDate() {
		return criationDate;
	}

	public void setCriationDate(LocalDate criationDate) {
		this.criationDate = criationDate;
	}

	public LocalDate getConclusionDate() {
		return conclusionDate;
	}

	public void setConclusionDate(LocalDate conclusionDate) {
		this.conclusionDate = conclusionDate;
	}

}
