package com.thorapetropoulosbuildingmanagement.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.thorapetropoulosbuildingmanagement.model.ApartmentUnit;
import com.thorapetropoulosbuildingmanagement.model.Issue;
import com.thorapetropoulosbuildingmanagement.model.IssueArchived;
import com.thorapetropoulosbuildingmanagement.model.ServiceProvided;
import com.thorapetropoulosbuildingmanagement.model.Task;
import com.thorapetropoulosbuildingmanagement.service.ApartmentUnitService;
import com.thorapetropoulosbuildingmanagement.service.IssueArchivedService;
import com.thorapetropoulosbuildingmanagement.service.IssueService;
import com.thorapetropoulosbuildingmanagement.service.ServiceProvidedService;

import com.thorapetropoulosbuildingmanagement.utilities.IssueStatusValues;

import com.thorapetropoulosbuildingmanagement.utilities.IssueCategories;

@Controller
public class IssueController {

	private static final Logger logger = LoggerFactory.getLogger(IssueController.class);

	@Autowired
	private IssueService issueService;

	@Autowired
	private ServiceProvidedService serviceProvidedService;

	@Autowired
	private ApartmentUnitService apartmentUnitService;

	@Autowired
	private IssueArchivedService issueArchivedService;

	@GetMapping("/addNewIssue")
	public String showNewIssueProvidedForm(Model model) {

		// create model attribute to bind form data
		Issue issue = new Issue();
		model.addAttribute("issueObject", issue);

		List<String> issueCategories = new ArrayList<>();
		issueCategories.add(IssueCategories.CARPENTRY.value);
		issueCategories.add(IssueCategories.CLEANING.value);
		issueCategories.add(IssueCategories.ELECTRIC.value);
		issueCategories.add(IssueCategories.GENERAL.value);
		issueCategories.add(IssueCategories.PLUMBING.value);

		model.addAttribute("issueCategories", issueCategories);
		System.out.println(issueCategories + " issue categories");

		List<String> issueStatus = new ArrayList<>();
		issueStatus.add(IssueStatusValues.CLOSED.value);
		issueStatus.add(IssueStatusValues.NEWISSUE.value);
		issueStatus.add(IssueStatusValues.PENDING.value);

		model.addAttribute("issueStatus", issueStatus);

		List<ServiceProvided> listProviders = serviceProvidedService.findAll();

		model.addAttribute("listServices", listProviders);

		List<ApartmentUnit> listApartments = apartmentUnitService.findAll();

		model.addAttribute("listApartments", listApartments);

		return "newIssueForm";
	}

	@PostMapping("/saveIssue")
	public String saveIssue(@ModelAttribute("issueObject") Issue issue,
			@RequestParam(name = "idApartment") String idApartment,
			@RequestParam(name = "idService") String idService) {
		// save service to database
		System.out.println("********This is the serviceProviderId " + Integer.parseInt(idService));

		Integer theServiceId = Integer.parseInt(idService);
		ServiceProvided sProvided = serviceProvidedService.findById(theServiceId);
		System.out.println("This is the service object " + sProvided);
		issue.setService(sProvided);

		Integer theApartmentId = Integer.parseInt(idApartment);
		ApartmentUnit apUnit = apartmentUnitService.findByApartmentUnitNumber(theApartmentId);
		System.out.println("This is the apartment object " + apUnit);
		issue.setApartmentUnit(apUnit);

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date(System.currentTimeMillis());

		issue.setIssueStartDate(date);

		issueService.save(issue);

		return "redirect:/listIssues";
	}

	@PostMapping("/saveUpdatedIssue")
	public String saveUpdatedIssue(@ModelAttribute("issueObject") Issue issue,
			@RequestParam(name = "idApartment") String idApartment, @RequestParam(name = "idService") String idService,
			@RequestParam(name = "openDate") String openDate) {
		// save service to database
		Integer theServiceId = Integer.parseInt(idService);
		ServiceProvided sProvided = serviceProvidedService.findById(theServiceId);
		System.out.println("This is the service object " + sProvided);
		issue.setService(sProvided);

		Integer theApartmentId = Integer.parseInt(idApartment);
		ApartmentUnit apUnit = apartmentUnitService.findByApartmentUnitNumber(theApartmentId);
		System.out.println("This is the apartment object " + apUnit);
		issue.setApartmentUnit(apUnit);

		try {
			Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(openDate);
			issue.setIssueStartDate(date1);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (issue.getStatus().equals("Closed")) {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date(System.currentTimeMillis());
			issue.setIssueCloseDate(date);
		}

		issueService.save(issue);

		return "redirect:/listIssues";
	}

	@GetMapping("/archiveIssue/{id}")
	public String archiveIssue(@PathVariable(value = "id") Integer id) {

		Issue issue = issueService.findById(id);

		String pattern = "MM/dd/yyyy";
		DateFormat df = new SimpleDateFormat(pattern);
		String closeDate = df.format(issue.getIssueCloseDate());

		String apartmentUnit = Integer.toString(issue.getApartmentUnit().getApartmentUnitNumber());
		IssueArchived ia = new IssueArchived();
		ia.setIssueDescription(issue.getIssueDescription());
		ia.setIssueStartDate(issue.getIssueCloseDate());
		ia.setIssueCloseDate(issue.getIssueCloseDate());
		ia.setStatus(issue.getStatus());
		ia.setApartmentName(apartmentUnit);
		ia.setServiceName(issue.getService().getCompanyName());

		issueArchivedService.save(ia);

		issueService.deleteById(issue.getIssueId());

		return "redirect:/listIssues";
	}

	@GetMapping("/showFormForUpdateIssue/{id}")
	public String showFormForUpdateIssue(@PathVariable(value = "id") Integer id, Model model) {

		Issue issueObject = issueService.findById(id);

		model.addAttribute("issueObject", issueObject);

		List<String> issueCategories = new ArrayList<>();
		issueCategories.add(issueObject.getIssueCategory());

		model.addAttribute("issueCategories", issueCategories);
		
//		List<String> issueCategoriesList = new ArrayList<>();
//		issueCategories.add(IssueCategories.CARPENTRY.value);
//		issueCategories.add(IssueCategories.CLEANING.value);
//		issueCategories.add(IssueCategories.ELECTRIC.value);
//		issueCategories.add(IssueCategories.GENERAL.value);
//		issueCategories.add(IssueCategories.PLUMBING.value);
//
//		model.addAttribute("issueCategoriesList", issueCategories);

		List<String> issueStatus = new ArrayList<>();
		issueStatus.add(IssueStatusValues.CLOSED.value);
		issueStatus.add(IssueStatusValues.NEWISSUE.value);
		issueStatus.add(IssueStatusValues.PENDING.value);

		model.addAttribute("issueStatus", issueStatus);

		// List<ServiceProvided> listProviders = serviceProvidedService.findAll();

		List<ServiceProvided> listServices = new ArrayList<ServiceProvided>();

		ServiceProvided listService = serviceProvidedService.findById(issueObject.getService().getServiceId());
		listServices.add(listService);
		model.addAttribute("listServices", listServices);
		
		List<ServiceProvided> listProviders = serviceProvidedService.findAll();

		model.addAttribute("listServicesAll", listProviders);

		String nameService = issueObject.getService().getCompanyName();
		model.addAttribute("nameService", nameService);
		// model.addAttribute("listServices", listProviders);

		ApartmentUnit au = issueObject.getApartmentUnit();
		String idApartment = Integer.toString(au.getApartmentUnitNumber());
		model.addAttribute("apartmentUnit", au);
		model.addAttribute("idApartment", idApartment);

		String idService = Integer.toString(issueObject.getService().getServiceId());
		model.addAttribute("idService", idService);

		String pattern = "MM/dd/yyyy";
		DateFormat df = new SimpleDateFormat(pattern);
		String openDate = df.format(issueObject.getIssueStartDate());

		model.addAttribute("openDate", openDate);

		return "updateIssueForm";

	}

	@GetMapping("/listIssues")
	public String viewIssueList(Model model) {
		logger.info("******************** Hello from IssueController");

		logger.trace("This is a trace log example");
		logger.info("This is an info log example");
		logger.debug("This is a debug log example");
		logger.error("This is an error log example");
		logger.warn("This is a warn log example");
		
		String result = "";
		try {
	     
			result =  findPaginated(1, "issueCategory", "asc", model);
		
		} catch(Exception e) {
			
		throw new ApiRequestException("List not found");
		
		}
		return result;

//		List<Issue> allIssues = issueService.findAll();
//		model.addAttribute("listIssues", allIssues);		
//		return "listOfIssues";
	}

	@GetMapping("/page4/{pageNo}")
	public String findPaginated(@PathVariable(value = "pageNo") int pageNo, String sortField, String sortDir,
			Model model) {
		int pageSize = 5;

		Page<Issue> page = issueService.findPaginated(pageNo, pageSize, sortField, sortDir);

		List<Issue> listIssues = page.getContent();

		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("listIssues", listIssues);

		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

		return "listOfIssues";
	}

}
