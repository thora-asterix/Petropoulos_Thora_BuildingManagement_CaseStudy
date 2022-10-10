package com.thorapetropoulosbuildingmanagement.utilities;

/*
 * This enum was created to display categories of tasks in the task UI pull down menu
 */
public enum TaskCategories {
	
	GENERALCLEANING("Genereal Cleaning"),
	GENERALREPAIR("General Repair"),
	POOLMAINTANANCE("Pool Maintanance");
	
	public String value;
	
	TaskCategories(String value){
		this.value = value;
	}

}
