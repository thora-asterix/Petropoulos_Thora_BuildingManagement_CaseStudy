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

import com.thorapetropoulosbuildingmanagement.model.Issue;
import com.thorapetropoulosbuildingmanagement.model.ServiceProvided;
import com.thorapetropoulosbuildingmanagement.model.Task;
import com.thorapetropoulosbuildingmanagement.model.Tenant;
import com.thorapetropoulosbuildingmanagement.service.IssueService;
import com.thorapetropoulosbuildingmanagement.service.ServiceProvidedService;
import com.thorapetropoulosbuildingmanagement.service.TaskService;
import com.thorapetropoulosbuildingmanagement.utilities.ServiceCategories;


@Controller
public class ServiceProvidedController {
	
	@Autowired
	private ServiceProvidedService serviceProvidedService;
	@Autowired
	private IssueService issueService;
	@Autowired
	private TaskService taskService;

    @GetMapping("/addNewServiceProvided")
    public String showNewServiceProvidedForm(Model model) {
    	
    	// create model attribute to bind form data
    	ServiceProvided serviceProvidedTemp = new ServiceProvided();
    	model.addAttribute("serviceProvided", serviceProvidedTemp);	
    	
    	List<String> serviceTypes = new ArrayList<>();
    	serviceTypes.add(ServiceCategories.CARPENTER.value);
    	serviceTypes.add(ServiceCategories.CARPETCLEANING.value);
    	serviceTypes.add(ServiceCategories.GENERALCLEANING.value);
    	serviceTypes.add(ServiceCategories.LOCKSMITH.value);
    	serviceTypes.add(ServiceCategories.PLUMBER.value);
    	serviceTypes.add(ServiceCategories.POOLMAINTANANCE.value);
    	serviceTypes.add(ServiceCategories.WINDOWCLEANING.value);
    	
    	
    	for(String s : serviceTypes) {
    		System.out.println(s);
    	}
    	
    	model.addAttribute("serviceCategories", serviceTypes);
    	
    	return "newServiceProvidedForm";
    }
    
    @PostMapping("/saveServiceProvided")
    public String saveServiceProvided(@ModelAttribute("serviceProvided") ServiceProvided servicePro ) {
    	// save service to database

    	serviceProvidedService.save(servicePro);		
    	return "redirect:/listServicesProvided";
    }
    
	
    @PostMapping("/saveUpdatedServiceProvided")
    public String saveUpdatedServiceProvided(@ModelAttribute("serviceProvided") ServiceProvided servicePro ) {

    	serviceProvidedService.save(servicePro);		
    	return "redirect:/listServicesProvided";
    }
    
    
	@GetMapping("/listServicesProvided")
	public String viewServiceList(Model model) {
		
		List<ServiceProvided> allProvidedServices = serviceProvidedService.findAll();
		model.addAttribute("listProvidedServices", allProvidedServices);
		
		return "listOfProvidedServices";
	}
	
	
	@GetMapping("/showFormForDeleteServiceProvided/{id}")
	public String showFormForDeleteServiceProvided(@PathVariable (value = "id") Integer id, Model model ) {
		
		ServiceProvided serviceProvidedTemp;
		
		List<Issue> listIssues = issueService.findAll();
		List<Task> listTasks = taskService.findAll();
		boolean checkService = false;
		
		for(Issue i: listIssues) {
			System.out.println(i);
			if(i.getService().getServiceId() == id) {
				checkService = true;
			}
		}
		
		for(Task t: listTasks) {
			if(t.getService().getServiceId() == id) {
				checkService = true;
			}
		}
		
		if(checkService) {
			serviceProvidedTemp = new ServiceProvided();
			serviceProvidedTemp.setCompanyName("Invalid");
			
		} else {
			serviceProvidedTemp = serviceProvidedService.findById(id);
		}
		
		model.addAttribute("serviceProvided",serviceProvidedTemp);
		
		return "deleteServiceProvidedForm";
		
	}
	
	// new - double check for functionality
	@GetMapping("/deleteServiceProvided")
	public String deleteServiceProvided(@ModelAttribute("serviceProvided") ServiceProvided servicePro) {
		serviceProvidedService.deleteById(servicePro.getServiceId());
			
	   return "redirect:/listServicesProvided";
	}

	@GetMapping("/showFormForUpdateServiceProvided/{id}")
	public String showFormForUpdateServiceProvided(@PathVariable (value = "id") Integer id, Model model ) {
		
		ServiceProvided serviceProvidedTemp = serviceProvidedService.findById(id);
		
		model.addAttribute("serviceProvided",serviceProvidedTemp);
		
    	List<String> serviceTypes = new ArrayList<>();
    	serviceTypes.add(ServiceCategories.CARPENTER.value);
    	serviceTypes.add(ServiceCategories.CARPETCLEANING.value);
    	serviceTypes.add(ServiceCategories.GENERALCLEANING.value);
    	serviceTypes.add(ServiceCategories.LOCKSMITH.value);
    	serviceTypes.add(ServiceCategories.PLUMBER.value);
    	serviceTypes.add(ServiceCategories.POOLMAINTANANCE.value);
    	serviceTypes.add(ServiceCategories.WINDOWCLEANING.value);
    	
  
    	model.addAttribute("serviceCategories", serviceTypes);
		
		return "updateServiceProvidedForm";
		
	}
}
