package com.thorapetropoulosbuildingmanagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="tasks")
public class Task {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="task_id")
	private Integer taskId;
	
	@Column(name="task_name")
	private String taskName;
	
	@Column(name="task_description")
	private String taskDescription;
	
	@Column(name="status")
	private String status;
	
	@OneToOne
	private ServiceProvided service;
	
	
	public Task() {
		
	}
	
	public Task(String taskName, String taskDescription, String status) {

		this.taskName = taskName;
		this.taskDescription = taskDescription;
		this.status = status;
	}
	
	public Task(String taskName, String taskDescription, String status, ServiceProvided service) {

		this.taskName = taskName;
		this.taskDescription = taskDescription;
		this.status = status;
		this.service = service;
	}

	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getTaskDescription() {
		return taskDescription;
	}

	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}


	public ServiceProvided getService() {
		return service;
	}

	public void setService(ServiceProvided service) {
		this.service = service;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
