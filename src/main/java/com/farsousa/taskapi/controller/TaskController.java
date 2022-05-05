package com.farsousa.taskapi.controller;

import java.net.URI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.farsousa.taskapi.dto.request.save.TaskRequestSave;
import com.farsousa.taskapi.dto.request.update.TaskRequetUpdate;
import com.farsousa.taskapi.dto.response.TaskResponse;
import com.farsousa.taskapi.enumeration.StatusTask;
import com.farsousa.taskapi.model.Task;
import com.farsousa.taskapi.repository.TaskRepository;

@RestController
@RequestMapping("/task")
@CrossOrigin(origins = "*")
public class TaskController {
	
	@Autowired
	private TaskRepository taskRepository;
	
	@PostMapping("/")
	public ResponseEntity<TaskResponse> save(@Valid @RequestBody TaskRequestSave taskRequestSave, UriComponentsBuilder uriBuilder){
		try {
			Task task = taskRequestSave.convertTaskRequestSaveToTask();
			taskRepository.save(task);
			URI uri = uriBuilder.path("/ponto/{id}").buildAndExpand(task.getId()).toUri();
			return ResponseEntity.created(uri).body(new TaskResponse(task));	
		}catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@GetMapping("/")
	public List<TaskResponse> listAll(){
		List<Task> tasks = new ArrayList<>();
		tasks = taskRepository.findAll();
		return TaskResponse.convertTaskListToTaskResponseList(tasks);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TaskResponse> listUsingId(@PathVariable Long id){
		try {
			Task task = taskRepository.getById(id);
			return ResponseEntity.ok(new TaskResponse(task));
		}catch (EntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		}catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@PatchMapping("/{id}")
	@Transactional
	public ResponseEntity<TaskResponse> update(@PathVariable Long id, @RequestBody TaskRequetUpdate taskRequestUpdate){
		try {
			Task task = taskRepository.getById(id);	
			
			if (taskRequestUpdate.getTitle() != null && !(taskRequestUpdate.getTitle().isBlank())) 
				task.setTitle(taskRequestUpdate.getTitle());
			if (taskRequestUpdate.getDescription() != null && !(taskRequestUpdate.getDescription().isBlank())) 
				task.setDescription(taskRequestUpdate.getDescription());
			if (taskRequestUpdate.getStatus() != null && !(taskRequestUpdate.getStatus().isBlank())) {
				if (taskRequestUpdate.getStatus().equals("Fazendo")) {
					task.setStatus(StatusTask.Fazendo);
					task.setConclusionDate(null);
				}else {
					if (taskRequestUpdate.getStatus().equals("Concluída")) {
						task.setStatus(StatusTask.Concluída);
						task.setConclusionDate(LocalDate.now());
					}else {
						task.setStatus(StatusTask.Aberta);
						task.setConclusionDate(null);
					}
				}
			}			
			
			return ResponseEntity.ok(new TaskResponse(task));
		}catch (EntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		}catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> remove(@PathVariable Long id) {
		try {
			taskRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}catch (Exception e) {
			return ResponseEntity.notFound().build();
		}		
	}
	
	

}
