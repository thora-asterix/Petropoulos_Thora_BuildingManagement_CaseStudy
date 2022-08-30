package com.thorapetropoulosbuildingmanagement.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="issues")
public class Issue {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="issue_id")
	private Integer issueId;
	
	@Column(name="issue_category")
	private String issueCategory;
	
	@Column(name="issue_description")
	private String issueDescription;
	
	@Column(name="issue_startdate")
	private Date issueStartDate;
	
	@Column(name="issue_closedate")
	private Date issueCloseDate;
	
	@Column(name="status")
	private String status;
	
	@ManyToOne
	private ApartmentUnit apartmentUnit;
	
	@ManyToOne
	private ServiceProvided service;
	
	public Issue() {
		
	}
	
	public Issue(String issueCategory, String issueDescription, Date issueStartDate, String status) {

		this.issueCategory = issueCategory;
		this.issueDescription = issueDescription;
		this.issueStartDate = issueStartDate;
		this.status = status;
	}
	

	public Issue(String issueCategory, String issueDescription, String status) {
		this.issueCategory = issueCategory;
		this.issueDescription = issueDescription;
		this.status = status;
	}
	
	
	public Issue(String issueCategory,String issueDescription, Date issueStartDate, Date issueCloseDate, String status) {
		this.issueCategory = issueCategory;
		this.issueDescription = issueDescription;
		this.issueStartDate = issueStartDate;
		this.issueCloseDate = issueCloseDate;
		this.status = status;
	}

	public Integer getIssueId() {
		return issueId;
	}

	public void setIssueId(Integer issueId) {
		this.issueId = issueId;
	}

	public String getIssueDescription() {
		return issueDescription;
	}

	public void setIssueDescription(String issueDescription) {
		this.issueDescription = issueDescription;
	}

	public String getIssueCategory() {
		return issueCategory;
	}

	public void setIssueCategory(String issueCategory) {
		this.issueCategory = issueCategory;
	}

	
	@DateTimeFormat(pattern="dd-MMM-YYYY")
	public Date getIssueStartDate() {
		return issueStartDate;
	}

	@DateTimeFormat(pattern="dd-MMM-YYYY")
	public void setIssueStartDate(Date issueStartDate) {
		this.issueStartDate = issueStartDate;
	}

	@DateTimeFormat(pattern="dd-MMM-YYYY")
	public Date getIssueCloseDate() {
		return issueCloseDate;
	}

	@DateTimeFormat(pattern="dd-MMM-YYYY")
	public void setIssueCloseDate(Date issueCloseDate) {
		this.issueCloseDate = issueCloseDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public ApartmentUnit getApartmentUnit() {
		return apartmentUnit;
	}

	public void setApartmentUnit(ApartmentUnit apartmentUnit) {
		this.apartmentUnit = apartmentUnit;
	}

	public ServiceProvided getService() {
		return service;
	}

	public void setService(ServiceProvided service) {
		this.service = service;
	}

	
}
