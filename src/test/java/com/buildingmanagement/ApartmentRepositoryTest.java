package com.buildingmanagement;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.thorapetropoulosbuildingmanagement.model.ApartmentUnit;
import com.thorapetropoulosbuildingmanagement.model.Tenant;
import com.thorapetropoulosbuildingmanagement.repository.ApartmentUnitRepository;

import com.thorapetropoulosbuildingmanagement.utilities.ApartmentStatusValues;

class ApartmentRepositoryTest {
	

	@Autowired
	private ApartmentUnitRepository repoApartment;
	
	@Test
	void testSaveApartmentUnit() {
		
		ApartmentUnit apUnit2 = new ApartmentUnit(1003,1,3,2,ApartmentStatusValues.AVAILABLE.value);
		assertNotNull(apUnit2);
	}


}
