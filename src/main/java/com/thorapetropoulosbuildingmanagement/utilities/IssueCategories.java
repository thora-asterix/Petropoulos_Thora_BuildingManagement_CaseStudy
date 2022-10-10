package com.thorapetropoulosbuildingmanagement.utilities;
/*
 * This enum is used to create categories for the issues UI pull down menu
 */

public enum IssueCategories {

	GENERAL("General"),
	ELECTRIC("Electric"),
	PLUMBING("Plumbing"),
	CARPENTRY("Carpentry"),
	CLEANING("Cleaning");
	
	public String value;
	
	IssueCategories(String value){
		this.value = value;
	}
}
