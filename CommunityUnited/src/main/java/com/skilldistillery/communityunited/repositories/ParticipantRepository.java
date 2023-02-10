package com.skilldistillery.communityunited.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.communityunited.entities.Participant;
import com.skilldistillery.communityunited.entities.ParticipantId;

public interface ParticipantRepository extends JpaRepository<Participant, Integer> {
	
	Participant findById(ParticipantId participantId);

}
