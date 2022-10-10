package com.thorapetropoulosbuildingmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.thorapetropoulosbuildingmanagement.model.Issue;
import com.thorapetropoulosbuildingmanagement.model.IssueArchived;
import com.thorapetropoulosbuildingmanagement.model.Task;
import com.thorapetropoulosbuildingmanagement.repository.IssueArchivedRepository;

@Service
public class IssueArchivedService {
	
	@Autowired
	private IssueArchivedRepository issueArchivedRepository;
	// get all archived issues
	public List<IssueArchived> findAll(){
		return issueArchivedRepository.findAll();
	}
	
	// get archived issues by id
	public IssueArchived findById(Integer id) {
		return issueArchivedRepository.findById(id).get();
	}
	
	// delete archived issues
	public void deleteById(Integer id) {
		issueArchivedRepository.deleteById(id);
	}
	
	// save/update archived issues
	public void save(IssueArchived issueArchived) {
		issueArchivedRepository.save(issueArchived);
	}
	
	public Page<IssueArchived> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection ){
		
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : 
			Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		
		return issueArchivedRepository.findAll(pageable);	
	}

}
