package com.thorapetropoulosbuildingmanagement.controller;

import java.util.ArrayList;
import java.util.List;

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
    	serviceTypes.add(ServiceCategories.ELECTRICIAN.value);
    	serviceTypes.add(ServiceCategories.POOLMAINTANANCE.value);
    	serviceTypes.add(ServiceCategories.WINDOWCLEANING.value);
    	
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
		
		String result = "";
		try {
	     
			result =  findPaginated(1, "companyName", "asc", model);
		
		} catch(Exception e) {
			
		throw new ApiRequestException("List not found");
		
		}
		return result;
		

//		List<ServiceProvided> allProvidedServices = serviceProvidedService.findAll();
//		model.addAttribute("listProvidedServices", allProvidedServices);
//		
//		return "listOfProvidedServices";
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
		
		
		
//    	List<String> serviceTypes = new ArrayList<>();
//    	serviceTypes.add(serviceProvidedTemp.getServiceType());
//    	model.addAttribute("serviceCategories", serviceTypes);
    	
     	List<String> serviceTypes = new ArrayList<>();
    	serviceTypes.add(ServiceCategories.CARPENTER.value);
    	serviceTypes.add(ServiceCategories.CARPETCLEANING.value);
    	serviceTypes.add(ServiceCategories.GENERALCLEANING.value);
    	serviceTypes.add(ServiceCategories.LOCKSMITH.value);
    	serviceTypes.add(ServiceCategories.PLUMBER.value);
    	serviceTypes.add(ServiceCategories.ELECTRICIAN.value);
    	serviceTypes.add(ServiceCategories.POOLMAINTANANCE.value);
    	serviceTypes.add(ServiceCategories.WINDOWCLEANING.value);
    	
    	model.addAttribute("serviceCategories", serviceTypes);
		
		return "updateServiceProvidedForm";
		
	}
	
	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo ,@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		int pageSize = 5;
		
		Page<ServiceProvided> page = serviceProvidedService.findPaginated(pageNo, pageSize, sortField, sortDir);
	
		List<ServiceProvided> listServiceProvided = page.getContent();
		System.out.println("List of paginated objects : "+listServiceProvided);
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("listProvidedServices", listServiceProvided);
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc": "asc");
		
		return "listOfProvidedServices";
	}
}
