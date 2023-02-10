package com.skilldistillery.communityunited.services;

import java.util.List;

import com.skilldistillery.communityunited.entities.VolunteerEvent;

public interface VolunteerEventService {

	VolunteerEvent create(VolunteerEvent event, int id);

	List<VolunteerEvent> allEvents();

	VolunteerEvent getEvent(int eventId);
	
	VolunteerEvent update(VolunteerEvent event, int id);

	
}
