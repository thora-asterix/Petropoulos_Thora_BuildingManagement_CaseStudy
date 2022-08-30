package com.thorapetropoulosbuildingmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thorapetropoulosbuildingmanagement.model.ApartmentUnit;



@Repository
public interface ApartmentUnitRepository extends JpaRepository<ApartmentUnit, Integer> {
	
	// get apartment unit by apartment number
	public ApartmentUnit findByApartmentUnitNumber(Integer num);	

}
