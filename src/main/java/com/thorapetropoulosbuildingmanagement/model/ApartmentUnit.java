package com.thorapetropoulosbuildingmanagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;



@Entity
@Table(name="apartment_units")
public class ApartmentUnit {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ap_id")
	private Integer apartmentId;
	
	@Column(name="ap_number")
	private Integer apartmentUnitNumber;
	
	@Column(name="floor_number")
	private Integer floorNumber;
	
	@Column(name="bedroom_number")
	private Integer bedroomNumber;
	
	@Column(name="bath_number")
	private Integer bathroomNumber;

	@Column(name="rental_status")
	private String rentalStatus;

	@OneToOne
	private Tenant tenant;
	
	
	public ApartmentUnit() {
		
	}
	
	public ApartmentUnit(Integer apartmentUnitNumber, Integer floorNumber, Integer bedroomNumber,
			Integer bathroomNumber,  String rentalStatus) {
		this.apartmentUnitNumber = apartmentUnitNumber;
		this.floorNumber = floorNumber;
		this.bedroomNumber = bedroomNumber;
		this.bathroomNumber = bathroomNumber;
		this.rentalStatus = rentalStatus;
		
	}
	
	public ApartmentUnit(Integer apartmentUnitNumber, Integer floorNumber, Integer bedroomNumber,
			Integer bathroomNumber,  String rentalStatus, Tenant tenant) {
		this.apartmentUnitNumber = apartmentUnitNumber;
		this.floorNumber = floorNumber;
		this.bedroomNumber = bedroomNumber;
		this.bathroomNumber = bathroomNumber;
		this.rentalStatus = rentalStatus;
		this.tenant = tenant;
	}

	public Integer getApartmentId() {
		return apartmentId;
	}

	public void setApartmentId(Integer apartmentId) {
		this.apartmentId = apartmentId;
	}

	public Integer getApartmentUnitNumber() {
		return apartmentUnitNumber;
	}

	public void setApartmentUnitNumber(Integer apartmentUnitNumber) {
		this.apartmentUnitNumber = apartmentUnitNumber;
	}

	public Integer getFloorNumber() {
		return floorNumber;
	}

	public void setFloorNumber(Integer floorNumber) {
		this.floorNumber = floorNumber;
	}

	public Integer getBedroomNumber() {
		return bedroomNumber;
	}

	public void setBedroomNumber(Integer bedroomNumber) {
		this.bedroomNumber = bedroomNumber;
	}

	public Integer getBathroomNumber() {
		return bathroomNumber;
	}

	public void setBathroomNumber(Integer bathroomNumber) {
		this.bathroomNumber = bathroomNumber;
	}


	public String getRentalStatus() {
		return rentalStatus;
	}

	public void setRentalStatus(String rentalStatus) {
		this.rentalStatus = rentalStatus;
	}
	
	public Tenant getTenant() {
		return tenant;
	}

	public void setTenant(Tenant tenant) {
		this.tenant = tenant;
	}


}
