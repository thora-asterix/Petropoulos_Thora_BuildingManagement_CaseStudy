package com.thorapetropoulosbuildingmanagement.model;
/*
 * An archived issue is no longer active and it is stored for reference
 */

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="issues_archived")
public class IssueArchived {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="issue_id")
	private Integer issueId;
	
	@Column(name="issue_description")
	private String issueDescription;
	
	@Column(name="issue_startdate")
	private Date issueStartDate;
	
	@Column(name="issue_closedate")
	private Date issueCloseDate;
	
	@Column(name="issue_status")
	private String status;
	
	@Column(name="apartment_name")
	private String apartmentName;
	
	@Column(name="service_name")
	private String serviceName;
	

	
	public IssueArchived() {
		
	}
	
	
	public IssueArchived(String issueDescription, Date issueStartDate, Date issueCloseDate, String status, String apartmentName, String serviceName) {
		super();
		this.issueDescription = issueDescription;
		this.issueStartDate = issueStartDate;
		this.issueCloseDate = issueCloseDate;
		this.status = status;
		this.apartmentName = apartmentName;
		this.serviceName = serviceName;
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

	public Date getIssueStartDate() {
		return issueStartDate;
	}

	public void setIssueStartDate(Date issueStartDate) {
		this.issueStartDate = issueStartDate;
	}

	public Date getIssueCloseDate() {
		return issueCloseDate;
	}

	public void setIssueCloseDate(Date issueCloseDate) {
		this.issueCloseDate = issueCloseDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getApartmentName() {
		return apartmentName;
	}

	public void setApartmentName(String apartmentName) {
		this.apartmentName = apartmentName;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}


	@Override
	public int hashCode() {
		return Objects.hash(apartmentName, issueCloseDate, issueDescription, issueId, issueStartDate, serviceName,
				status);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IssueArchived other = (IssueArchived) obj;
		return Objects.equals(apartmentName, other.apartmentName)
				&& Objects.equals(issueCloseDate, other.issueCloseDate)
				&& Objects.equals(issueDescription, other.issueDescription) && Objects.equals(issueId, other.issueId)
				&& Objects.equals(issueStartDate, other.issueStartDate)
				&& Objects.equals(serviceName, other.serviceName) && Objects.equals(status, other.status);
	}

}
