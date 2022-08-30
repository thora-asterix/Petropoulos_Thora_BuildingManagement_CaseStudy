package com.thorapetropoulosbuildingmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.thorapetropoulosbuildingmanagement.model.ApartmentUnit;
import com.thorapetropoulosbuildingmanagement.model.Tenant;
import com.thorapetropoulosbuildingmanagement.service.ApartmentUnitService;
import com.thorapetropoulosbuildingmanagement.service.TenantService;



@Controller
public class TenantController {
	
	@Autowired
	private TenantService tenantService;
	@Autowired
	private ApartmentUnitService apService;
	
    @GetMapping("/addNewTenant")
    public String showNewTenantForm(Model model) {
    	// create model attribute to bind form data
    	Tenant tenant = new Tenant();
    	model.addAttribute("tenant", tenant);	
    	return "newTenantForm";
    }
    
    @PostMapping("/saveTenant")
    public String saveTeanant(@ModelAttribute("tenant") Tenant tenant ) {
    	// save tenant to database
    	tenantService.save(tenant);		
    	return "redirect:/listTenants";
    }
    
	@GetMapping("/listTenants")
	public String viewTenantList(Model model) {		
		List<Tenant> allTenants = tenantService.findAll();
		model.addAttribute("listTenants", allTenants);		
		return "listOfTenants";
	}

	@GetMapping("/showFormForUpdateTenant/{id}")
	public String showFormForUpdateTenant(@PathVariable (value = "id") Integer id, Model model ) {	
		Tenant tenantTemp = tenantService.findById(id);
		model.addAttribute("tenant",tenantTemp);	
		return "updateTenantForm";	
	}
	
	@GetMapping("/showFormForDeleteTenant/{id}")
	public String showFormForDeleteTenant(@PathVariable (value = "id") Integer id, Model model ) {	
		Tenant tenantTemp;
		List<ApartmentUnit> apartList = apService.findAll();
		boolean checkTenant = false;
		for(ApartmentUnit au : apartList) {
			System.out.println(au);
			if(au.getTenant() != null) {
			  System.out.println(au.getTenant());
			  if(au.getTenant().getTenantId() == id) {
				  checkTenant = true;
				  System.out.println("Tenant associated with apartment");
			  }
			}		
		}
		if(checkTenant) {
		   tenantTemp = new Tenant();
		   tenantTemp.setFirstName("Invalid");
		} else {
		   tenantTemp = tenantService.findById(id);
		}
		
		model.addAttribute("tenant",tenantTemp);	
		return "deleteTenantForm";	
	}
	
	
	// new - double check for functionality
	@GetMapping("/deleteTenant")
	public String deleteTenant(@ModelAttribute("tenant") Tenant tenant) {
		tenantService.deleteById(tenant.getTenantId());		
	   return "redirect:/listTenants";
	}
}
