package com.skilldistillery.communityunited.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.communityunited.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	User findByEmail(String email);

}
