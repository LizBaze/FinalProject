package com.skilldistillery.communityunited.services;

import java.util.List;

import com.skilldistillery.communityunited.entities.GroupMessage;

public interface GroupMessageService {

	public GroupMessage createMessage(GroupMessage message, int eventId, String email);
	
	public List<GroupMessage> findByEventId(int id);
	
	public boolean deleteMessage(int messageId);
}
