package com.buildingmanagement;

import static org.junit.Assert.assertNotNull;


import java.util.List;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.thorapetropoulosbuildingmanagement.PetropoulosThoraBuildingManagementCaseStudyApplication;
import com.thorapetropoulosbuildingmanagement.model.ServiceProvided;

import com.thorapetropoulosbuildingmanagement.service.ServiceProvidedService;
import com.thorapetropoulosbuildingmanagement.utilities.ServiceCategories;


@SpringBootTest(classes = PetropoulosThoraBuildingManagementCaseStudyApplication.class)
public class ServiceProvidedRepositoryTest {
	
	@Autowired
	private ServiceProvidedService repoService;

	@Test
	public void testFindById() {
		ServiceProvided serviceExpected = new ServiceProvided("Tomy Cleaning Service",ServiceCategories.GENERALCLEANING.value,"May Tomy","222-567-5678", "tomy@work");
		serviceExpected.setServiceId(32);		
		ServiceProvided serviceActual = repoService.findById(32);
		System.out.println(serviceActual.equals(serviceExpected));
		System.out.println("Contact name: "+serviceExpected.getContactFullName());
		System.out.println(serviceExpected.getCompanyName()+ " "+ serviceExpected.getContactFullName()+ " "+ serviceExpected.getEmail()+" "+serviceExpected.getPhoneNumber());
		Assertions.assertEquals(serviceExpected,serviceActual);
		
	}
	
	@Test
	public void printAllService() {
		List<ServiceProvided> serviceList = repoService.findAll();
		System.out.println(serviceList);
	}
	

	@Test
	void testSaveService() {
		ServiceProvided serviceOne = new ServiceProvided("Bella Cleaning Service",ServiceCategories.GENERALCLEANING.value,"Gina Bella","222-567-5678","bella@work");
		repoService.save(serviceOne);
			
		ServiceProvided serviceTest = repoService.findById(2);
		
		assertNotNull(serviceTest);
	}


//	@Test
//	public void printAllService() {
//		List<ServiceProvided> serviceList = repoService.findAll();
//		System.out.println(serviceList);
//	}

}
