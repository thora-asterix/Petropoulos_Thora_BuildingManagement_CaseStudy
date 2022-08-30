package com.thorapetropoulosbuildingmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.thorapetropoulosbuildingmanagement.model.Task;



@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

}
