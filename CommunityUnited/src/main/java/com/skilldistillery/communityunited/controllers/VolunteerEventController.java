package com.skilldistillery.communityunited.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
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

import com.skilldistillery.communityunited.entities.VolunteerEvent;
import com.skilldistillery.communityunited.services.VolunteerEventService;

@RestController
@CrossOrigin({"*", "http://localhost"})
@RequestMapping("api")
public class VolunteerEventController {

	@Autowired
	private VolunteerEventService eventService;
	
	@GetMapping("volunteerevents")
	public List<VolunteerEvent> events() {
		return eventService.allEvents();
	}
	
	@GetMapping("volunteerevents/{eid}")
	public VolunteerEvent event(HttpServletRequest req, HttpServletResponse res, @PathVariable int eid) {
		VolunteerEvent event = eventService.getEvent(eid);
		if(event == null) {
			res.setStatus(404);
		}
		return event;
	}
	
	@PostMapping("organizations/{oid}/volunteerevents")
	public VolunteerEvent createEvent(@RequestBody VolunteerEvent event, @PathVariable int oid, HttpServletResponse res) {
		System.out.println(event);
		event = eventService.create(event, oid);
		if(event != null) {
			res.setStatus(201);
		  }
		  else {
			  res.setStatus(400);
		  }
		return event;
		}
	
	@PutMapping("volunteerevents/{id}")
	public VolunteerEvent updated(HttpServletRequest req, HttpServletResponse res, @RequestBody VolunteerEvent event, @PathVariable int id) {
		try {
			event = eventService.update(event, id);
			if(event == null) {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			event = null;
		}
		return event;
	}
	
	

}
