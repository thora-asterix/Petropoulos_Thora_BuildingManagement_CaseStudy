package com.thorapetropoulosbuildingmanagement.utilities;

public enum TaskStatusValues {
	
	PENDING("Pending"),
	COMPLETED("Completed");
	
	public String value;
	
	TaskStatusValues(String value){
		this.value = value;
	}

}
