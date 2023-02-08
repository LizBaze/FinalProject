package com.skilldistillery.communityunited.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.skilldistillery.communityunited.entities.User;
import com.skilldistillery.communityunited.repositories.UserRepository;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private UserRepository userRepo;

	@Override
	public User register(User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		user.setEnabled(true);
		user.setRole("standard");
		userRepo.saveAndFlush(user);
		return user;
	}

	@Override
	public User getUserByEmail(String email) {
		return userRepo.findByEmail(email);
	}

	@Override
	public User update(User user, int id) {
		Optional<User> userOpt = userRepo.findById(id);
		User updatedUser = null;
		if (userOpt.isPresent()) {
			updatedUser = userOpt.get();
			updatedUser.setEmail(user.getEmail());
			updatedUser.setFirstName(user.getFirstName());
			updatedUser.setLastName(user.getLastName());
			updatedUser.setImgUrl(user.getImgUrl());
			updatedUser.setBio(user.getBio());
			userRepo.saveAndFlush(updatedUser);
		}

		return updatedUser;
	}

}
