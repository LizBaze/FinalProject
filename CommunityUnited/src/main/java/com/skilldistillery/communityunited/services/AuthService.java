package com.skilldistillery.communityunited.services;

import com.skilldistillery.communityunited.entities.User;

public interface AuthService {
	
	public User register(User user);
	public User getUserByEmail(String email);
	public User update(User user, int id);
	public boolean disabledAccount(int id);
		

}
