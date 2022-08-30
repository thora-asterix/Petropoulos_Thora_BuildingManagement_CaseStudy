package com.thorapetropoulosbuildingmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
		
		List<IssueArchived> allArchivedIssues = issueArchivedService.findAll();
		model.addAttribute("listArchiveIssues", allArchivedIssues);
		
		return "listOfArchivedIssues";
	}
	


}
