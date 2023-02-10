package com.skilldistillery.communityunited.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.communityunited.entities.Organization;
import com.skilldistillery.communityunited.entities.VolunteerEvent;
import com.skilldistillery.communityunited.repositories.AddressRepository;
import com.skilldistillery.communityunited.repositories.OrganizationRepository;
import com.skilldistillery.communityunited.repositories.VolunteerEventRepository;

@Service
public class VolunteerEventServiceImpl implements VolunteerEventService {
	
	@Autowired
	private VolunteerEventRepository volunteerEventRepo;
	
	@Autowired
	private OrganizationRepository orgRepo;
	
	@Autowired
	private AddressRepository addressRepo;

	@Override
	public VolunteerEvent create(VolunteerEvent event, int id) {
		Optional<VolunteerEvent> newEvent = volunteerEventRepo.findById(event.getId());
		Optional<Organization> org = orgRepo.findById(id);
		
		if(!newEvent.isPresent() && org.isPresent()) {
			event.setAddress(addressRepo.saveAndFlush(event.getAddress()));
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
	
	@Override
	public VolunteerEvent update(VolunteerEvent event, int id) {
		Optional<VolunteerEvent> updateOpt = volunteerEventRepo.findById(id);
		VolunteerEvent update = new VolunteerEvent();
		if (updateOpt.isPresent()) {
			update = updateOpt.get();
			update.setName(event.getName());
			update.setDescription(event.getDescription());
			update.setCreatedDate(event.getCreatedDate());
			update.setStartDate(event.getStartDate());
			update.setEndDate(event.getEndDate());
	
		}
		return volunteerEventRepo.saveAndFlush(update);
		
	
	}

}
