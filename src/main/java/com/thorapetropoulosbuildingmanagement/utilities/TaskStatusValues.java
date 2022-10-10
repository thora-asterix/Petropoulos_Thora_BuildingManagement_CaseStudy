package com.thorapetropoulosbuildingmanagement.utilities;
/*
 * This enum was created to display categories for the tasks status in the task UI pull down menu
 */

public enum TaskStatusValues {
	
	PENDING("Pending"),
	COMPLETED("Completed");
	
	public String value;
	
	TaskStatusValues(String value){
		this.value = value;
	}

}
