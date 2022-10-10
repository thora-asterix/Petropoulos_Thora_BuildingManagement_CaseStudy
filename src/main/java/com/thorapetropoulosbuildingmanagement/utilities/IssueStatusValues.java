package com.thorapetropoulosbuildingmanagement.utilities;

/*
 * This enum is used to create categories for the issues UI pull down menu
 */
public enum IssueStatusValues {
	
	NEWISSUE("new Issue"),
	PENDING("Pending"),
	CLOSED("Closed");

	public String value;
	
	IssueStatusValues(String value){
		this.value = value;
	}
}
