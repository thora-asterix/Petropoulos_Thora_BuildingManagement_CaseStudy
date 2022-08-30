package com.thorapetropoulosbuildingmanagement.utilities;

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
