package com.skilldistillery.communityunited.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	
	@GetMapping("organizations") 
		public List<Organization> index(Principal principal, HttpServletResponse res) {
			return orgService.findAll();
		}
	
	
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
	
	
	@PutMapping("organizations/{id}")
	public Organization updateOrg(@RequestBody Organization org, @PathVariable Integer id, Principal principal, HttpServletResponse res) {
		
		org = orgService.updated(org, id, principal.getName());
		if (org != null ) {
			res.setStatus(200);
		} else {
			res.setStatus(400);
		}
		
		return org;
	}
	
	@GetMapping("organizations/{id}/users/{userId}")
	public boolean checkAdmin(@PathVariable int id, @PathVariable int userId, HttpServletResponse res, Principal principal) {
		boolean admin = orgService.checkAdmin(id, userId);
		
		return admin;
	}

}
