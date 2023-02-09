package com.skilldistillery.communityunited.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.communityunited.entities.VolunteerEvent;

public interface VolunteerEventRepository extends JpaRepository<VolunteerEvent, Integer> {

}
