package com.skilldistillery.communityunited.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.communityunited.entities.GroupMessage;
import com.skilldistillery.communityunited.services.GroupMessageService;

@RestController
@CrossOrigin({"*", "http://localhost"})
@RequestMapping("api")
public class GroupMessageController {
	
	
	@Autowired
	private GroupMessageService messageService;
	
	@PostMapping("volunteerevents/{id}/groupmessages")
	public GroupMessage create(@PathVariable int id, Principal principal, @RequestBody GroupMessage message, HttpServletResponse res) {
		message = messageService.createMessage(message, id, principal.getName());
		if (message != null) {
			res.setStatus(201);
		} else {
			res.setStatus(400);
		}
		
		return message;
	}
	
	@GetMapping("volunteerevents/{id}/groupmessages")
	public List<GroupMessage> findByEvent(@PathVariable int id, HttpServletResponse res){
		List<GroupMessage> messages = messageService.findByEventId(id);
		if (messages != null) {
			res.setStatus(200);
		} else {
			res.setStatus(404);
		}
		return messages;
	}
	
	@DeleteMapping("groupmessages/{id}")
	public void delete(@PathVariable int id, HttpServletResponse res) {
		boolean deleted = messageService.deleteMessage(id);
		if (deleted) {
			res.setStatus(204);
		} else {
			res.setStatus(400);
		}
	}

}
