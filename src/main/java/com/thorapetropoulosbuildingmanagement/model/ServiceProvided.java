package com.thorapetropoulosbuildingmanagement.model;
/*
 * The ServiceProvided class is used to created objects that represent companies that provide a service to the building. 
 * Issues and Task have services assigned to them
 */

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="services")
public class ServiceProvided {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="service_id")
	private Integer serviceId;
	
	@Column(name="company_name")
	private String companyName;
	
	@Column(name="service_type")
	private String serviceType;
	
	@Column(name="contact_name")
	private String contactFullName;
	
	@Column(name="phone_number")
	private String phoneNumber;
	
	@Column(name="email")
	private String email;
	
	public ServiceProvided() {
		
	}
	
	public ServiceProvided(String companyName, String serviceType, String contactFullName,
			String phoneNumber, String email) {
		this.companyName = companyName;
		this.serviceType = serviceType;
		this.contactFullName = contactFullName;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}

	public Integer getServiceId() {
		return serviceId;
	}

	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public String getContactFullName() {
		return contactFullName;
	}

	public void setContactFullName(String contactFullName) {
		this.contactFullName = contactFullName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(companyName, contactFullName, email, phoneNumber, serviceId, serviceType);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ServiceProvided other = (ServiceProvided) obj;
		return Objects.equals(companyName, other.companyName) && Objects.equals(contactFullName, other.contactFullName)
				&& Objects.equals(email, other.email) && Objects.equals(phoneNumber, other.phoneNumber)
				&& Objects.equals(serviceId, other.serviceId) && Objects.equals(serviceType, other.serviceType);
	}

}
