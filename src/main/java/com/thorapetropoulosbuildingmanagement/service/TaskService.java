package com.thorapetropoulosbuildingmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thorapetropoulosbuildingmanagement.model.Task;
import com.thorapetropoulosbuildingmanagement.repository.TaskRepository;


@Service
public class TaskService {

	@Autowired
	private TaskRepository taskRepository;
	
	// get all tasks
	public List<Task> findAll(){
		return taskRepository.findAll();
	}
	
	// get tasks by id
	public Task findById(Integer id) {
		return taskRepository.findById(id).get();
	}
	
	// delete tasks
	public void deleteById(Integer id) {
		taskRepository.deleteById(id);
	}
	
	// save/update tasks
	public void save(Task task) {
		taskRepository.save(task);
	}
}
