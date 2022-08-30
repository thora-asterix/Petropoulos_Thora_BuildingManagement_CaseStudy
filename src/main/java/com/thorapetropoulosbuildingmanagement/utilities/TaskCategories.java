package com.thorapetropoulosbuildingmanagement.utilities;

public enum TaskCategories {
	
	GENERALCLEANING("Genereal Cleaning"),
	GENERALREPAIR("General Repair"),
	POOLMAINTANANCE("Pool Maintanance");
	
	public String value;
	
	TaskCategories(String value){
		this.value = value;
	}

}
