package com.thorapetropoulosbuildingmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thorapetropoulosbuildingmanagement.model.Issue;
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

}
