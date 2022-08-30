package com.thorapetropoulosbuildingmanagement.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.thorapetropoulosbuildingmanagement.model.ServiceProvided;
import com.thorapetropoulosbuildingmanagement.model.Task;
import com.thorapetropoulosbuildingmanagement.model.Tenant;
import com.thorapetropoulosbuildingmanagement.repository.ServiceProvidedRepository;
import com.thorapetropoulosbuildingmanagement.service.ServiceProvidedService;
import com.thorapetropoulosbuildingmanagement.service.TaskService;
import com.thorapetropoulosbuildingmanagement.utilities.ServiceCategories;
import com.thorapetropoulosbuildingmanagement.utilities.TaskCategories;
import com.thorapetropoulosbuildingmanagement.utilities.TaskStatusValues;


@Controller
public class TaskController {
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private ServiceProvidedService serviceProvided;
	
	 
	  @GetMapping("/addNewTask")
	    public String showNewTaskForm(Model model) {
	    	
	    	// create model attribute to bind form data
	        Task task = new Task();
	    	model.addAttribute("taskObject", task);	
	    	
	    	List<String> taskTypes = new ArrayList<>();
            taskTypes.add(TaskCategories.GENERALCLEANING.value);
            taskTypes.add(TaskCategories.GENERALREPAIR.value);
            taskTypes.add(TaskCategories.POOLMAINTANANCE.value);	
	    	
	    	model.addAttribute("taskCategories", taskTypes);
	    	
	    	
	    	List<String> taskStatus = new ArrayList<>();
	    	taskStatus.add(TaskStatusValues.COMPLETED.value);
	    	taskStatus.add(TaskStatusValues.PENDING.value);
	    	
	    	model.addAttribute("taskStatus",taskStatus);
	    	
	
	    	List<ServiceProvided> listServices = serviceProvided.findAll();
	    	
	    	model.addAttribute("listServices",listServices);
	    	
	    	
	    	return "newTaskForm";
	    }
	    
//	    @PostMapping("/saveTask")
//	    public String saveTask(@ModelAttribute("taskObject") Task task ) {
//	    	// save service to database
//	  
//	    	taskService.save(task);		
//	    	return "redirect:/listTasks";
//	    }
	    
	    @PostMapping("/saveTask")
	    public String saveTask(@ModelAttribute("taskObject") Task task, @RequestParam(name = "id") String id ) {
	    	// save service to database
	    	System.out.println("********This is the serviceProviderId "+ Integer.parseInt(id));
	
	    	Integer theId = Integer.parseInt(id);
	    	ServiceProvided sProvided = serviceProvided.findById(theId);
	 	    System.out.println("This is the service object "+ sProvided);
            
	     	task.setService(sProvided);
	    	taskService.save(task);		
	    	return "redirect:/listTasks";
	    }
	    
	    @PostMapping("/saveUpdatedTask")
	    public String saveUpatedTask(@ModelAttribute("taskObject") Task task, @RequestParam(name = "id") String id) {
	    	// save service to database
	    	System.out.println("********This is the serviceProviderId "+ Integer.parseInt(id));
	
	    	Integer theId = Integer.parseInt(id);
	    	ServiceProvided sProvided = serviceProvided.findById(theId);
	 	    System.out.println("This is the service object "+ sProvided);
            
	     	task.setService(sProvided);
	    	taskService.save(task);		
	    	return "redirect:/listTasks";
	    }
	    
	
	@GetMapping("/listTasks")
	public String viewTaskList(Model model) {
		
		List<Task> allTask = taskService.findAll();
		model.addAttribute("listTasks", allTask);
		
		return "listOfTasks";
	}
	
	@GetMapping("/showFormForUpdateTask/{id}")
	public String showFormForUpdateTask(@PathVariable (value = "id") Integer id, Model model ) {
		
		Task taskObject = taskService.findById(id);
		
		model.addAttribute("taskObject",taskObject);
		
	  	List<String> taskTypes = new ArrayList<>();
        taskTypes.add(TaskCategories.GENERALCLEANING.value);
        taskTypes.add(TaskCategories.GENERALREPAIR.value);
        taskTypes.add(TaskCategories.POOLMAINTANANCE.value);	
    	
    	model.addAttribute("taskCategories", taskTypes);
    	
    	
    	List<String> taskStatus = new ArrayList<>();
    	taskStatus.add(TaskStatusValues.COMPLETED.value);
    	taskStatus.add(TaskStatusValues.PENDING.value);
    	
    	model.addAttribute("taskStatus",taskStatus);
    	
    	String idService = Integer.toString(taskObject.getService().getServiceId());
    	model.addAttribute("id",idService);
		
		return "updateTaskForm";
		
	}
	
	@GetMapping("/showFormFormDeleteTask/{id}")
	public String showFormFormDeleteTask(@PathVariable (value = "id") Integer id, Model model ) {
		
		Task taskObject = taskService.findById(id);
		
		model.addAttribute("taskObj",taskObject);
		
		
		return "deleteTaskForm";		
	}

	@GetMapping("/deleteTask")
	public String deleteTask(@ModelAttribute("taskObj") Task task) {
		taskService.deleteById(task.getTaskId());
          
	   return "redirect:/listTasks";
	}
}
