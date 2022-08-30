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

import com.thorapetropoulosbuildingmanagement.model.ApartmentUnit;
import com.thorapetropoulosbuildingmanagement.model.ServiceProvided;
import com.thorapetropoulosbuildingmanagement.model.Task;
import com.thorapetropoulosbuildingmanagement.model.Tenant;
import com.thorapetropoulosbuildingmanagement.service.ApartmentUnitService;
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
	    
	    @PostMapping("/saveUpdatedApartment")
	    public String saveUpatedApartment(@ModelAttribute("apartmentUnit") ApartmentUnit apartmentUnit, @RequestParam(name = "tenantId") String tenantId ) {
	    	// save service to database
	    	System.out.println("********This is the serviceProviderId "+ Integer.parseInt(tenantId));
	
	    	Integer theId = Integer.parseInt(tenantId);
	    	Tenant tenantTemp = tenantService.findById(theId);
	 	    System.out.println("This is the service object "+ tenantTemp);
            
	        apartmentUnit.setTenant(tenantTemp);
	        apartmentUnitService.save(apartmentUnit);		
	    	return "redirect:/listApartments";
	    }
	    
	    
		
	@GetMapping("/listApartments")
	public String viewApartmentList(Model model) {
		
		List<ApartmentUnit> apartmentUnits = apartmentUnitService.findAll();
		System.out.println("This is the apartment unit list " + apartmentUnits);
		for(ApartmentUnit au : apartmentUnits) {
			System.out.println(au.getRentalStatus());
		}
		model.addAttribute("apartmentUnitsList", apartmentUnits);
		
		return "listOfApartmentUnits";
	}
	
	@GetMapping("/showFormForUpdateApartment/{id}")
	public String showFormForUpdateApartment(@PathVariable (value = "id") Integer id, Model model ) {
		
		ApartmentUnit aptUnit = apartmentUnitService.findById(id);
		
		model.addAttribute("apartmentUnit",aptUnit);
		
    	List<String> apartmentStatus = new ArrayList<>();
    	apartmentStatus.add(ApartmentStatusValues.AVAILABLE.value);
    	apartmentStatus.add(ApartmentStatusValues.OCCUPIED.value);
    	
    	model.addAttribute("apartmentStatus",apartmentStatus);
        
    	if(aptUnit.getTenant() == null) {
    		model.addAttribute("tenantId",null);
    	} else {
		String tenantId = Integer.toString(aptUnit.getTenant().getTenantId());
		model.addAttribute("tenantId",tenantId);
    	}
		
		return "updateApartmentForm";
		
	}

}
