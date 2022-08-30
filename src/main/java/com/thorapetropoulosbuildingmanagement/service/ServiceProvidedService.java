package com.thorapetropoulosbuildingmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thorapetropoulosbuildingmanagement.model.ServiceProvided;
import com.thorapetropoulosbuildingmanagement.repository.ServiceProvidedRepository;



@Service
public class ServiceProvidedService {

	@Autowired
	private ServiceProvidedRepository serviceRepository;
	
	// get all services
	public List<ServiceProvided> findAll(){
		return serviceRepository.findAll();
	}
	
	// get services by id
	public ServiceProvided findById(Integer id) {
		return serviceRepository.findById(id).get();
	}
	
	// delete services
	public void deleteById(Integer id) {
		serviceRepository.deleteById(id);
	}
	
	// save/update services
	public void save(ServiceProvided service) {
		serviceRepository.save(service);
	}
	
}
