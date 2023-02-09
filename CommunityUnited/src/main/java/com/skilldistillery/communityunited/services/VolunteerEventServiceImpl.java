package com.skilldistillery.communityunited.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.communityunited.entities.Organization;
import com.skilldistillery.communityunited.entities.VolunteerEvent;
import com.skilldistillery.communityunited.repositories.OrganizationRepository;
import com.skilldistillery.communityunited.repositories.VolunteerEventRepository;

@Service
public class VolunteerEventServiceImpl implements VolunteerEventService {
	
	@Autowired
	private VolunteerEventRepository volunteerEventRepo;
	
	@Autowired
	private OrganizationRepository orgRepo;

	@Override
	public VolunteerEvent create(VolunteerEvent event, int id) {
		Optional<VolunteerEvent> newEvent = volunteerEventRepo.findById(event.getId());
		Optional<Organization> org = orgRepo.findById(id);
		
		if(!newEvent.isPresent() && org.isPresent()) {
			event.setOrganization(org.get());
			volunteerEventRepo.saveAndFlush(event);
			return event;
		}
		
		return null;
	}

	@Override
	public List<VolunteerEvent> allEvents() {
		return volunteerEventRepo.findAll();
	}
	
	@Override
	public VolunteerEvent getEvent(int eventId) {
		VolunteerEvent event = null;
		Optional<VolunteerEvent> eventOpt = volunteerEventRepo.findById(eventId);
		if(eventOpt.isPresent()) {
			event = eventOpt.get();
		}
		return event;
	}

}
