package com.skilldistillery.communityunited.services;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.communityunited.entities.Participant;
import com.skilldistillery.communityunited.entities.ParticipantId;
import com.skilldistillery.communityunited.entities.User;
import com.skilldistillery.communityunited.entities.VolunteerEvent;
import com.skilldistillery.communityunited.repositories.ParticipantRepository;
import com.skilldistillery.communityunited.repositories.UserRepository;
import com.skilldistillery.communityunited.repositories.VolunteerEventRepository;

@Service
public class ParticipantServiceImpl implements ParticipantService {

	
	@Autowired
	private VolunteerEventRepository vRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private ParticipantRepository partRepo;
	
	
	
	@Override
	public Participant addParticipant(int eventId, String email) {
		VolunteerEvent event = null;
		Optional<VolunteerEvent> eventOpt = vRepo.findById(eventId);
		User user = userRepo.findByEmail(email);
		Participant participant = null;
		if(user != null && eventOpt.isPresent()) {
			event = eventOpt.get();
		    participant = new Participant();
			ParticipantId partId = new ParticipantId(); 
			partId.setUserId(user.getId());
			partId.setEventId(event.getId());
			participant.setDateJoined(LocalDateTime.now());
			participant.setId(partId);
			participant.setUser(user);
			participant.setVolunteerEvent(event);
			partRepo.saveAndFlush(participant);
			
		}
		
		return participant;
	}
	
	

}
