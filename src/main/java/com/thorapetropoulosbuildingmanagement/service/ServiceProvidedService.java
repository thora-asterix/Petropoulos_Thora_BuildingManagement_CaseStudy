package com.thorapetropoulosbuildingmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.thorapetropoulosbuildingmanagement.model.ServiceProvided;
import com.thorapetropoulosbuildingmanagement.model.Tenant;
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
	
//	public Page<ServiceProvided> findPaginated(int pageNo, int pageSize){
//		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
//		
//		return serviceRepository.findAll(pageable);
//	}
	
	public Page<ServiceProvided> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection){
		
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		
		return serviceRepository.findAll(pageable);	
	}
	
}
