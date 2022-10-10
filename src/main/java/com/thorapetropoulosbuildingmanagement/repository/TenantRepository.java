package com.thorapetropoulosbuildingmanagement.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thorapetropoulosbuildingmanagement.model.Tenant;



@Repository
public interface TenantRepository extends JpaRepository<Tenant, Integer> {


}
