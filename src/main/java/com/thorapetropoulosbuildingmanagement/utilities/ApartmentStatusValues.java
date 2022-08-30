package com.thorapetropoulosbuildingmanagement.utilities;

public enum ApartmentStatusValues {
	
	OCCUPIED("Occupied"),
	AVAILABLE("Available");
	
	public String value;
	
    ApartmentStatusValues(String value){
    	this.value = value;
    }

}
