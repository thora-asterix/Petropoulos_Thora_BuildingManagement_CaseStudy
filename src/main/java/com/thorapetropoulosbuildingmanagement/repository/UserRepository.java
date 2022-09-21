package com.thorapetropoulosbuildingmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.thorapetropoulosbuildingmanagement.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	@Query("SELECT u FROM User u WHERE u.username = :username")
	public User findByUsername(String username);

}
