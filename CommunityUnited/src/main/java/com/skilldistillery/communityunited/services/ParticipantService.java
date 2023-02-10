package com.skilldistillery.communityunited.services;

import com.skilldistillery.communityunited.entities.Participant;

public interface ParticipantService {
	
	Participant addParticipant(int eventId, String email);
	
	

}
