package com.thorapetropoulosbuildingmanagement.utilities;
/*
 * This enum was created to created to create categories for the service UI pull down menu
 */

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
