package com.skilldistillery.communityunited.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.communityunited.entities.Organization;

public interface OrganizationRepository extends JpaRepository<Organization, Integer> {
	
	    Organization findByName(String name);

}
