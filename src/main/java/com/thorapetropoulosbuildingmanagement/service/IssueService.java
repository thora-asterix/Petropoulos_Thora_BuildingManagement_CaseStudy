package com.thorapetropoulosbuildingmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.thorapetropoulosbuildingmanagement.model.Issue;
import com.thorapetropoulosbuildingmanagement.model.Task;
import com.thorapetropoulosbuildingmanagement.model.Tenant;
import com.thorapetropoulosbuildingmanagement.repository.IssueRepository;

@Service
public class IssueService {
	
	@Autowired
	private IssueRepository issueRepository;
	
	// get all issues
	public List<Issue> findAll(){
		return issueRepository.findAll();
	}
	
	// get issue by id
	public Issue findById(Integer id) {
		return issueRepository.findById(id).get();
	}
	
	// delete issue
	public void deleteById(Integer id) {
		issueRepository.deleteById(id);
	}
	
	// save/update issue
	public void save(Issue issue) {
		issueRepository.save(issue);
	}

	public Page<Issue> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection ){
		
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : 
			Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		
		return issueRepository.findAll(pageable);	
	}
}
