package com.thorapetropoulosbuildingmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thorapetropoulosbuildingmanagement.model.ApartmentUnit;
import com.thorapetropoulosbuildingmanagement.model.Issue;



@Repository
public interface IssueRepository extends JpaRepository<Issue, Integer> {
	
	public List<Issue> findIssueByApartmentUnit(ApartmentUnit apartmentUnit);
	
	public List<Issue> findByStatusContaining(String name);

}
