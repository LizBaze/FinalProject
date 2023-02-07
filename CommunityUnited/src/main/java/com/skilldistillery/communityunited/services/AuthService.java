package com.skilldistillery.communityunited.services;

import com.skilldistillery.communityunited.entities.User;

public interface AuthService {
	
	public User register(User user);
	public User getUserByUsername(String username);

}
