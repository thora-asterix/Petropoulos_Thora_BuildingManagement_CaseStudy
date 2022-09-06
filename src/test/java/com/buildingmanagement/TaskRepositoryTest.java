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

class TaskRepositoryTest {
	
	@Autowired
	private ServiceProvidedRepository repoService;
	
	@Autowired
	private TaskRepository repoTask;

	@Test
	void testSaveTask() {
		ServiceProvided serviceOne = repoService.getById(2);
		
		Task taskOne = new Task(TaskCategories.GENERALCLEANING.value,"Clean carpets on second flooor",TaskStatusValues.PENDING.value);
		
		taskOne.setService(serviceOne);
		
		repoTask.save(taskOne);
		
		assertNotNull(taskOne);
	}
}
