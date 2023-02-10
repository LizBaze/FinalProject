package com.skilldistillery.communityunited.services;

import java.util.List;

import com.skilldistillery.communityunited.entities.Member;
import com.skilldistillery.communityunited.entities.Organization;

public interface OrganizationService {
	
	List<Organization> findAll();
	Organization create(Organization org, String email);
	Organization updated(Organization org, int id, String email);
	boolean checkAdmin(int orgId, int userId);
	
    Member addMemberToOrg(int orgId, String email);
    
    Organization findById(int id);
    
    boolean leaveOrganization(int orgId, String email);
	

}
