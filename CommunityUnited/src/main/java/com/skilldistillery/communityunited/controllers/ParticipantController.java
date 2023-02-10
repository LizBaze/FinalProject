package com.skilldistillery.communityunited.controllers;

import java.security.Principal;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.communityunited.entities.Participant;
import com.skilldistillery.communityunited.services.ParticipantService;

@RestController
@CrossOrigin({"*", "http://localhost"})
@RequestMapping("api")
public class ParticipantController {
	
	@Autowired
	private ParticipantService partService;
	
	
	@PostMapping("participants/{id}")
	public Participant addParticipant(@PathVariable int id, Principal principal, HttpServletResponse res) {
		Participant part = partService.addParticipant(id, principal.getName());
		if(part != null) {
			res.setStatus(201);
		}
		else {
			res.setStatus(400);
		}
		return part;
		
	}
	
	@DeleteMapping("volunteerevents/{id}/users")
	public void removeParticipant(@PathVariable int id, Principal principal, HttpServletResponse res) {
		boolean removed = partService.removeParticipant(id, principal.getName());
		if(removed == true) {
			res.setStatus(204);
		} else {
			res.setStatus(400);
		}
	}

}
