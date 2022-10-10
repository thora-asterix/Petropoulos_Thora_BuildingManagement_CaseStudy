package com.thorapetropoulosbuildingmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.thorapetropoulosbuildingmanagement.model.Task;
import com.thorapetropoulosbuildingmanagement.model.Tenant;
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
	
//	public Page<Task> findPaginated(int pageNo, int pageSize){
//		
//		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
//		
//		return taskRepository.findAll(pageable);	
//	}
//	
	public Page<Task> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection ){
		
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : 
			Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		
		return taskRepository.findAll(pageable);	
	}
	
}
