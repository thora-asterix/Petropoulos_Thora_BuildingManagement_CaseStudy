package com.thorapetropoulosbuildingmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thorapetropoulosbuildingmanagement.model.IssueArchived;



@Repository
public interface IssueArchivedRepository extends JpaRepository<IssueArchived, Integer> {

	
}
