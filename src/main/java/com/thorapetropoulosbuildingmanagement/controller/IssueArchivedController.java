package com.thorapetropoulosbuildingmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.thorapetropoulosbuildingmanagemen.exceptions.ApiRequestException;
import com.thorapetropoulosbuildingmanagement.model.Issue;
import com.thorapetropoulosbuildingmanagement.model.IssueArchived;
import com.thorapetropoulosbuildingmanagement.model.ServiceProvided;
import com.thorapetropoulosbuildingmanagement.model.Task;
import com.thorapetropoulosbuildingmanagement.service.IssueArchivedService;
import com.thorapetropoulosbuildingmanagement.service.IssueService;
import com.thorapetropoulosbuildingmanagement.service.TaskService;


@Controller
public class IssueArchivedController {
	
	@Autowired
	private IssueArchivedService issueArchivedService;
	
	
	@GetMapping("/listArchivedIssues")
	public String viewIssueArchived(Model model) {
		
		String result = "";
		try {
	     
			result = findPaginated(1, "apartmentName", "asc", model);
		
		} catch(Exception e) {
			
		throw new ApiRequestException("List not found");
		
		}
		return result;
		
//		List<IssueArchived> allArchivedIssues = issueArchivedService.findAll();
//		model.addAttribute("listArchiveIssues", allArchivedIssues);
//		
//		return "listOfArchivedIssues";
	}
	
	@GetMapping("/page5/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo, @RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		int pageSize = 5;
		
		Page<IssueArchived> page = issueArchivedService.findPaginated(pageNo, pageSize, sortField, sortDir);
	
		List<IssueArchived> listIssuesArchived = page.getContent();

		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("listArchivedIssues", listIssuesArchived);
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc": "asc");
		
		return "listOfArchivedIssues";
	}


}
