package com.skilldistillery.communityunited.controllers;

import java.security.Principal;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.communityunited.entities.Organization;
import com.skilldistillery.communityunited.services.OrganizationService;

@RestController
@CrossOrigin({"*", "http://localhost"})
@RequestMapping("api")
public class OrganizationController {
	
	@Autowired
	private OrganizationService orgService;
	

	
	@PostMapping("organizations")
	public Organization createOrg(@RequestBody Organization org, Principal principal, HttpServletResponse res) {
	  org = orgService.create(org, principal.getName());
	  if(org != null) {
		  res.setStatus(201);
	  }
	  else {
		  res.setStatus(400);
	  }
		
		return org;
		
	}

}
