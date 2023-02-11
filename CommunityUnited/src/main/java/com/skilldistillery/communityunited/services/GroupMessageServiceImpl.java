package com.skilldistillery.communityunited.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.communityunited.entities.GroupMessage;
import com.skilldistillery.communityunited.entities.User;
import com.skilldistillery.communityunited.entities.VolunteerEvent;
import com.skilldistillery.communityunited.repositories.GroupMessageRepository;
import com.skilldistillery.communityunited.repositories.UserRepository;
import com.skilldistillery.communityunited.repositories.VolunteerEventRepository;

@Service
public class GroupMessageServiceImpl implements GroupMessageService {

	
	@Autowired	
	private UserRepository userRepo;
	
	@Autowired
	private VolunteerEventRepository eventRepo;
	
	@Autowired
	private GroupMessageRepository messageRepo;
	
	
	public GroupMessage createMessage(GroupMessage message, int eventId, String email) {
		User user = userRepo.findByEmail(email);
		Optional<VolunteerEvent> eventOpt = eventRepo.findById(eventId);
		if (user != null && eventOpt.isPresent()) {
			VolunteerEvent event = eventOpt.get();
			message.setUser(user);
			message.setVolunteerEvent(event);
			message = messageRepo.saveAndFlush(message);
		}
		return message;
	}


	@Override
	public List<GroupMessage> findByEventId(int id) {
		List<GroupMessage> messages = messageRepo.findByVolunteerEvent_Id(id);
		return messages;
	}


	@Override
	public boolean deleteMessage(int messageId) {
		Optional<GroupMessage> messageOpt = messageRepo.findById(messageId);
		boolean deleted = false;
		if (messageOpt.isPresent()) {
			GroupMessage message = messageOpt.get();
			messageRepo.delete(message);
			deleted = true;
		}
		return deleted;
	}
}
