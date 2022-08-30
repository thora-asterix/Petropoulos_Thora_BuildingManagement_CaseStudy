package com.thorapetropoulosbuildingmanagement.utilities;

public enum IssueStatusValues {
	
	NEWISSUE("new Issue"),
	PENDING("Pending"),
	CLOSED("Closed");

	public String value;
	
	IssueStatusValues(String value){
		this.value = value;
	}
}
