package com.thorapetropoulosbuildingmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.thorapetropoulosbuildingmanagement.model.ApartmentUnit;
import com.thorapetropoulosbuildingmanagement.service.ApartmentUnitService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class AvailableApartments {

	@Autowired
	private ApartmentUnitService apartmentUnitService;
	
	
	@GetMapping("/availableApartments")
	public List<ApartmentUnit> listAvailableApartments(){
		return apartmentUnitService.findAllByRentalStatus("Available");
	}
	
	
}
