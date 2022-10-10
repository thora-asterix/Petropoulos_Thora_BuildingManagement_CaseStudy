package com.thorapetropoulosbuildingmanagement.utilities;

/*
 * This enum class is used to create default values for a pull down menu in the apartment UI
 */
public enum ApartmentStatusValues {
	
	OCCUPIED("Occupied"),
	AVAILABLE("Available");
	
	public String value;
	
    ApartmentStatusValues(String value){
    	this.value = value;
    }

}
