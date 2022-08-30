package com.thorapetropoulosbuildingmanagement.utilities;

public enum ServiceCategories {
	
	WINDOWCLEANING("Window Cleaning"),
	CARPETCLEANING("Carpet Cleaning"),
	GENERALCLEANING("General Cleaning"),
	POOLMAINTANANCE("Pool Maintanance"),
	ELECTRICIAN("Electrician"),
	PLUMBER("Plumber"),
	LOCKSMITH("Locksmith"),
	CARPENTER("Carpenter");
	
	
	public String value;
	
	ServiceCategories(String value){
	this.value=value;
	}
}
