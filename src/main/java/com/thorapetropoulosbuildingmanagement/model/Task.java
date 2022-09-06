package com.thorapetropoulosbuildingmanagement.model;

/*
 * The Task class  is used to create daily tasks that are associated with the building's maintanance
 */
import java.util.Objects;

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

	@Override
	public int hashCode() {
		return Objects.hash(service, status, taskDescription, taskId, taskName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Task other = (Task) obj;
		return Objects.equals(service, other.service) && Objects.equals(status, other.status)
				&& Objects.equals(taskDescription, other.taskDescription) && Objects.equals(taskId, other.taskId)
				&& Objects.equals(taskName, other.taskName);
	}
	
	

}
