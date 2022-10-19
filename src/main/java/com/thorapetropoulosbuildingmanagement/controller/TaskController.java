package com.thorapetropoulosbuildingmanagement.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.thorapetropoulosbuildingmanagemen.exceptions.ApiRequestException;
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
	    	
	    	List<ServiceProvided> listServicesUnsorted = serviceProvided.findAll();
	    	
	    	List<ServiceProvided> listServices = listServicesUnsorted.stream().sorted(Comparator.comparing(ServiceProvided::getServiceType))
	    			.collect(Collectors.toList());
	    	
	    	model.addAttribute("listServices",listServices);
	     	
	    	return "newTaskForm";
	    }
	    

	    @PostMapping("/saveTask")
	    public String saveTask(@ModelAttribute("taskObject") Task task, @RequestParam(name = "id") String id ) {
	    	// save service to database
	    	System.out.println("******************************* name of service is : "+id);
	
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
		String result = "";
		try {
	     
			result = findPaginated(1, "taskName", "asc", model);
		
		} catch(Exception e) {
			
		throw new ApiRequestException("List not found");
		
		}
		return result;
		
//		List<Task> allTask = taskService.findAll();
//		model.addAttribute("listTasks", allTask);
//		
//		return "listOfTasks";
	}
	
	@GetMapping("/showFormForUpdateTask/{id}")
	public String showFormForUpdateTask(@PathVariable (value = "id") Integer id, Model model ) {
		
		Task taskObject = taskService.findById(id);
		
		
		model.addAttribute("taskObject",taskObject);
		
		List<String> taskTypes = new ArrayList<>();
        taskTypes.add(TaskCategories.GENERALCLEANING.value);
        taskTypes.add(TaskCategories.GENERALREPAIR.value);
        taskTypes.add(TaskCategories.POOLMAINTANANCE.value);	
    	
    	model.addAttribute("taskCategoriesList", taskTypes);
	
		
	  	List<String> taskType = new ArrayList<>();
        taskType.add(taskObject.getTaskName());
       
    	model.addAttribute("taskCategories", taskType);
    	
    	
    	List<String> taskStatus = new ArrayList<>();
    	taskStatus.add(TaskStatusValues.COMPLETED.value);
    	taskStatus.add(TaskStatusValues.PENDING.value);
    	
    	model.addAttribute("taskStatus",taskStatus);
    	
    // 	List<ServiceProvided> listServices = new ArrayList<ServiceProvided>();
    	
 	List<ServiceProvided> listServices = serviceProvided.findAll();
    	
       ServiceProvided listService = serviceProvided.findById(taskObject.getService().getServiceId());
      //  model.addAttribute("companyName", listService.getCompanyName());
    //    listServices.add(listService);
    	model.addAttribute("listServices",listServices);
    	
    	String idService = Integer.toString(taskObject.getService().getServiceId());
    	model.addAttribute("id",idService);
    	
     	String nameService = taskObject.getService().getCompanyName();
    	model.addAttribute("nameService",nameService);
		
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
	
	@GetMapping("/page3/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo, @RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		int pageSize = 5;
		
		Page<Task> page = taskService.findPaginated(pageNo, pageSize, sortField, sortDir);
	
		List<Task> listTasks = page.getContent();

		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("listTasks", listTasks);
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc": "asc");
		
		return "listOfTasks";
	}
}
