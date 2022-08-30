package com.thorapetropoulosbuildingmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thorapetropoulosbuildingmanagement.model.Tenant;
import com.thorapetropoulosbuildingmanagement.repository.TenantRepository;

@Service
public class TenantService {

	@Autowired
	private TenantRepository tenantRepository;
	
	// get all tenants
	public List<Tenant> findAll(){
		return tenantRepository.findAll();
	}
	
	// get tenant by id
	public Tenant findById(Integer id) {
		return tenantRepository.findById(id).get();
	}
	
	// delete tenant
	public void deleteById(Integer id) {
		tenantRepository.deleteById(id);
	}
	
	// save/update tenant
	public void save(Tenant tenant) {
		tenantRepository.save(tenant);
	}
}



