package com.buildingmanagement;


import static org.junit.Assert.assertNotNull;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.thorapetropoulosbuildingmanagement.PetropoulosThoraBuildingManagementCaseStudyApplication;
import com.thorapetropoulosbuildingmanagement.model.ApartmentUnit;
import com.thorapetropoulosbuildingmanagement.model.Issue;
import com.thorapetropoulosbuildingmanagement.model.IssueArchived;
import com.thorapetropoulosbuildingmanagement.model.ServiceProvided;
import com.thorapetropoulosbuildingmanagement.model.Task;
import com.thorapetropoulosbuildingmanagement.model.Tenant;
import com.thorapetropoulosbuildingmanagement.repository.ApartmentUnitRepository;
import com.thorapetropoulosbuildingmanagement.repository.IssueArchivedRepository;
import com.thorapetropoulosbuildingmanagement.repository.IssueRepository;
import com.thorapetropoulosbuildingmanagement.repository.ServiceProvidedRepository;
import com.thorapetropoulosbuildingmanagement.repository.TaskRepository;
import com.thorapetropoulosbuildingmanagement.repository.TenantRepository;
import com.thorapetropoulosbuildingmanagement.utilities.ApartmentStatusValues;
import com.thorapetropoulosbuildingmanagement.utilities.IssueStatusValues;
import com.thorapetropoulosbuildingmanagement.utilities.ServiceCategories;
import com.thorapetropoulosbuildingmanagement.utilities.TaskCategories;
import com.thorapetropoulosbuildingmanagement.utilities.TaskStatusValues;


@SpringBootTest(classes = PetropoulosThoraBuildingManagementCaseStudyApplication.class)
class PetropoulosThoraBuildingManagementCaseStudyApplicationTests {
	@Autowired
	private ServiceProvidedRepository repoService;
	
	@Autowired
	private ApartmentUnitRepository repoApartment;
	
	@Autowired
	private TenantRepository repoTenant;
	
	
	@Autowired
	private TaskRepository repoTask;
	
	@Autowired 
	private IssueRepository repoIssue;
	
	@Autowired
	private IssueArchivedRepository repoIssueArchived;
	
	
	@Test
	void testGetApartmenRentalStatus() {
		
		List<ApartmentUnit> au = repoApartment.findAllByRentalStatus("Available");
		System.out.println(au + " apartment units avaialble");
		for(ApartmentUnit s: au) {
			System.out.println("status is "+ s.getRentalStatus());
		}
		assertNotNull(au);
	}

	

	@Test
	void testSaveApartmentUnit() {
		

	    Tenant oneTenant = repoTenant.getById(4);
	  
		ApartmentUnit apUnit2 = new ApartmentUnit(1003,1,3,2,ApartmentStatusValues.AVAILABLE.value);
		apUnit2.setTenant(oneTenant);
		
		repoApartment.save(apUnit2);
		
		assertNotNull(apUnit2);
	}

	
	@Test
	void testSaveTask() {
		ServiceProvided serviceOne = repoService.getById(2);
		
		Task taskOne = new Task(TaskCategories.GENERALCLEANING.value,"Clean carpets on second flooor",TaskStatusValues.PENDING.value);
		
		taskOne.setService(serviceOne);
		
		repoTask.save(taskOne);
		
		assertNotNull(taskOne);
	}

	
	@Test
	void testIssuesByApartment() {
		ApartmentUnit apUnit = repoApartment.findById(2).get();
		
		List<Issue> issues = repoIssue.findIssueByApartmentUnit(apUnit);
		System.out.println("Issues in aprartmnet 2: " + issues);
	}
	
	
	@Test
	void testIssueByStatus() {
		List<Issue> issues = repoIssue.findByStatusContaining("closed");
		System.out.println("Closed issues " + issues);
	}
	
//	@Test
//	void testSaveIssueArchived() {
//	
//        Issue isOne = repoIssue.findById(5).get();
//        Issue isTwo = new Issue(isOne.getIssueCategory(), isOne.getIssueDescription(),isOne.getIssueStartDate(),isOne.getIssueCloseDate(),isOne.getStatus());
//	    String apName = "1001";
//	    
//	    String serviceName = "The cleaning ladies";
//		
////	    (String issueDescription, Date issueStartDate, Date issueCloseDate, String status, String apartmentName, String serviceName
//	    IssueArchived issueArch = new IssueArchived(isTwo.getIssueDescription(),isTwo.getIssueStartDate(),isTwo.getIssueCloseDate(),isTwo.getStatus(),apName,serviceName);
//		
//	    repoIssueArchived.save(issueArch);
//	
//	    assertNotNull(issueArch);
//	}
}
