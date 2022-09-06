package com.thorapetropoulosbuildingmanagement.model;

/*
 * The Tenant class is used to create objects of tenants that rent the apartments in the building
 */

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tenants")
public class Tenant {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="tenant_id")
	private Integer tenantId;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="phone_number")
	private String phoneNumber;
	
	@Column(name="email")
	private String email;
	private Integer numberOfTenants;
	
	public Tenant() {
		
	}
	
	public Tenant(String firstName, String lastName, String phoneNumber, String email,
			Integer numberOfTenants) {

		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.numberOfTenants = numberOfTenants;
	}

	public Integer getTenantId() {
		return tenantId;
	}

	public void setTenantId(Integer tenantId) {
		this.tenantId = tenantId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	public Integer getNumberOfTenants() {
		return numberOfTenants;
	}

	public void setNumberOfTenants(Integer numberOfTenants) {
		this.numberOfTenants = numberOfTenants;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, firstName, lastName, numberOfTenants, phoneNumber, tenantId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tenant other = (Tenant) obj;
		return Objects.equals(email, other.email) && Objects.equals(firstName, other.firstName)
				&& Objects.equals(lastName, other.lastName) && Objects.equals(numberOfTenants, other.numberOfTenants)
				&& Objects.equals(phoneNumber, other.phoneNumber) && Objects.equals(tenantId, other.tenantId);
	}
	
	

}
