package com.skilldistillery.communityunited.services;

import com.skilldistillery.communityunited.entities.Organization;
import com.skilldistillery.communityunited.entities.User;

public interface OrganizationService {
	
	Organization create(Organization org, String email);
	
	

}
