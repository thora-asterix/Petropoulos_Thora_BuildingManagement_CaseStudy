package com.thorapetropoulosbuildingmanagement.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
import com.thorapetropoulosbuildingmanagement.model.ApartmentUnit;
import com.thorapetropoulosbuildingmanagement.model.Issue;
import com.thorapetropoulosbuildingmanagement.model.IssueArchived;
import com.thorapetropoulosbuildingmanagement.model.ServiceProvided;
import com.thorapetropoulosbuildingmanagement.model.Task;
import com.thorapetropoulosbuildingmanagement.model.Tenant;
import com.thorapetropoulosbuildingmanagement.service.ApartmentUnitService;
import com.thorapetropoulosbuildingmanagement.service.IssueService;
import com.thorapetropoulosbuildingmanagement.service.ServiceProvidedService;
import com.thorapetropoulosbuildingmanagement.service.TenantService;
import com.thorapetropoulosbuildingmanagement.utilities.TaskCategories;
import com.thorapetropoulosbuildingmanagement.utilities.TaskStatusValues;
import com.thorapetropoulosbuildingmanagement.utilities.ApartmentStatusValues;


@Controller
public class ApartmentUnitController {
	
	@Autowired
	private ApartmentUnitService apartmentUnitService;
	
	@Autowired
	private TenantService tenantService;
	
	@Autowired
	private IssueService issueService;
	
	/*
	 * display a new apartment form. Dropdowns are prepopulated with enum values and a new Aparment Unit object is passed to the model as an attributed
	 */
	  @GetMapping("/addNewApartment")
	    public String showNewApartmentForm(Model model) {
	    	
	    	// create model attribute to bind form data
	        ApartmentUnit apUnit = new ApartmentUnit();
	    	model.addAttribute("apartmentUnit", apUnit);	
	    	
	    	List<String> apartmentStatus = new ArrayList<>();
	    	apartmentStatus.add(ApartmentStatusValues.AVAILABLE.value);
	    	apartmentStatus.add(ApartmentStatusValues.OCCUPIED.value);
	    	
	    	model.addAttribute("apartmentStatus",apartmentStatus);
	    	
	    	return "newApartmentForm";
	    }
	    
	    @PostMapping("/saveApartmentUnit")
	    public String saveApartment(@ModelAttribute("apartmentUnit") ApartmentUnit apartmentUnit ) {
	    	// save service to database
	   
	    	apartmentUnitService.save(apartmentUnit);
	    	return "redirect:/listApartments";
	    }
	    
	    /*
	     * ApartmentUnit object is bind to the form and passed to the method as a ModelAttribute parameter
	     */
	    @PostMapping("/saveUpdatedApartment")
	    public String saveUpatedApartment(@ModelAttribute("apartmentUnit") ApartmentUnit apartmentUnit, @RequestParam(name = "tenantId") String tenantId ) {
	    	// save service to database
	    	
	    	System.out.println("********This is the serviceProviderId "+ Integer.parseInt(tenantId));
	
	    	Integer theId = Integer.parseInt(tenantId);
	    	if(theId != 222) {
	    		Tenant tenantTemp = tenantService.findById(theId);
		 	    System.out.println("This is the service object "+ tenantTemp); 	   
		        apartmentUnit.setTenant(tenantTemp);  
		        apartmentUnit.setRentalStatus("Occupied");
		        
	    	}
	    	
	    	// make sure apartment is status availble if no tenant
	    	
          	if(theId == 222){
          		apartmentUnit.setRentalStatus("Available");
          	}
	        apartmentUnitService.save(apartmentUnit);		
	    	return "redirect:/listApartments";
	    }
	    
		@GetMapping("/showFormFormDeleteApartment/{id}")
		public String deleteApt(@PathVariable(value = "id") Integer id, Model model) {
			
			ApartmentUnit apartmentUnit;
			List<Issue> issues = issueService.findAll();
			boolean checkApt = false;
			for(Issue i : issues) {
		
				if(i.getApartmentUnit() != null) {
				
				  if(i.getApartmentUnit().getApartmentId() == id) {
					  checkApt = true;
					  System.out.println("Issue associated with apartment");
				  }
				}		
			}
			
			if(checkApt || apartmentUnitService.findById(id).getRentalStatus().equals("Occupied")) {
				apartmentUnit = new ApartmentUnit();
				apartmentUnit.setApartmentUnitNumber(0000); 
			} else {
				apartmentUnit = apartmentUnitService.findById(id);
			}
			
	

		  model.addAttribute("aptUnit",apartmentUnit);
		  

			return "deleteApartmentForm";
		}  
		
		
		// new - double check for functionality
		@GetMapping("/deleteApartment")
		public String deleteApartment(@ModelAttribute("aptUnit") ApartmentUnit aptUnit) {
			apartmentUnitService.deleteById(aptUnit.getApartmentId());
				
		   return "redirect:/listApartments";
		}


		
	@GetMapping("/listApartments")
	public String viewApartmentList(Model model) {
		
		String result = "";
		try {
	     
			result = findPaginated(1, "apartmentUnitNumber", "asc", model);
		
		} catch(Exception e) {
			
		throw new ApiRequestException("List not found");
		
		}
		return result;
		

		
//		List<ApartmentUnit> apartmentUnits = apartmentUnitService.findAll();
//		System.out.println("This is the apartment unit list " + apartmentUnits);
//		for(ApartmentUnit au : apartmentUnits) {
//			System.out.println(au.getRentalStatus());
//		}
//		model.addAttribute("apartmentUnitsList", apartmentUnits);
//		
//		return "listOfApartmentUnits";
	}
	
	@GetMapping("/showFormForUpdateApartment/{id}")
	public String showFormForUpdateApartment(@PathVariable (value = "id") Integer id, Model model ) {
		
		ApartmentUnit aptUnit = apartmentUnitService.findById(id);
		
		model.addAttribute("apartmentUnit",aptUnit);
		
    	List<String> apartmentStatus = new ArrayList<>();
    	apartmentStatus.add(ApartmentStatusValues.AVAILABLE.value);
    	apartmentStatus.add(ApartmentStatusValues.OCCUPIED.value);
    	
    	model.addAttribute("apartmentStatus",apartmentStatus);
    	
    	List<Tenant> listTenants = new ArrayList<Tenant>();
    	
    	if(aptUnit.getTenant() == null) {
    	Tenant temp = new Tenant();
    	temp.setTenantId(222);
    	temp.setEmail("NO TENANT OPTION");
    	temp.setFirstName("NO TENANT");
    	temp.setLastName("NO TENANT");
    	temp.setPhoneNumber("1111111111");
    	temp.setNumberOfTenants(1);
    	listTenants.add(temp);
    	listTenants.addAll(tenantService.findAll());
    	model.addAttribute("listTenants",listTenants);
    	} else {
    		
    		String emailTenant = aptUnit.getTenant().getEmail();
        	model.addAttribute("emailTenant",emailTenant);
        	
    		Tenant temp = new Tenant();
        	temp.setTenantId(222);
        	temp.setEmail("NO TENANT OPTION");
        	temp.setFirstName("NO TENANT");
        	temp.setLastName("NO TENANT");
        	temp.setPhoneNumber("1111111111");
        	temp.setNumberOfTenants(1);
        	listTenants.add(temp);
    		listTenants.add(aptUnit.getTenant());
    		model.addAttribute("listTenants",listTenants);
    	}
    	

    	if(aptUnit.getTenant() == null) {
    		model.addAttribute("tenantId",null);
    	} else {
		String tenantId = Integer.toString(aptUnit.getTenant().getTenantId());
		model.addAttribute("tenantId",tenantId);
    	}
		
		return "updateApartmentForm";
		
	}

	@GetMapping("/page6/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo, @RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		int pageSize = 5;
		Page<ApartmentUnit> page = apartmentUnitService.findPaginated(pageNo, pageSize, sortField, sortDir);
	
		List<ApartmentUnit> apartmentUnitList = page.getContent();

		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("apartmentUnitsList", apartmentUnitList);
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc": "asc");
		
		return "listOfApartmentUnits";
	}
}
