package com.thorapetropoulosbuildingmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thorapetropoulosbuildingmanagement.model.ServiceProvided;



@Repository
public interface ServiceProvidedRepository extends JpaRepository<ServiceProvided, Integer> {

}
