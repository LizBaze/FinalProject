package com.skilldistillery.communityunited.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.communityunited.entities.Participant;

public interface ParticipantRepository extends JpaRepository<Participant, Integer> {
	
	

}
