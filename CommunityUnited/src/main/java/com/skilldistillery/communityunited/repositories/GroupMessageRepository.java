package com.skilldistillery.communityunited.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.communityunited.entities.GroupMessage;


public interface GroupMessageRepository extends JpaRepository<GroupMessage, Integer> {

	List<GroupMessage> findByVolunteerEvent_Id(int id);
	
}
